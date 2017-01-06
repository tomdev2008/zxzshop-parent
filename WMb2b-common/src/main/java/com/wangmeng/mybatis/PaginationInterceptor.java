package com.wangmeng.mybatis;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wangmeng.common.pagination.PageInfo;
import com.wangmeng.common.utils.ReflectHelper;
import com.wangmeng.mybatis.dialect.Dialect;
import com.wangmeng.mybatis.dialect.DialectFactory;
import com.wangmeng.query.ASimpleFilter;
import com.wangmeng.query.TargetOpMode;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： PaginationInterceptor          <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  分页等特性数据拦截器
 *     整合朱博士的原有分页拦截器
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor extends ASimpleFilterExtractor implements Interceptor {

	/**
	 * LOG
	 */
	private final static Logger log = LoggerFactory.getLogger(PaginationInterceptor.class);
	
	/**
	 * 默认ObjectFactory (用于兼容MYBATIS 3.2)
	 */
	public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	
	/**
	 * 默认ObjectWrapperFactory(用于兼容MYBATIS 3.2)
	 */
	public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	

//	/**
//	 * 默认ReflectorFactory (用于兼容MYBATIS 3.2)
//	 */
//	public static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new org.apache.ibatis.reflection.DefaultReflectorFactory();
	
	
	/**
	 * 属性名称:数据库方言类名
	 */
	private final static String dialectClassCandName = "dialectClassCand";
	
	/**
	 * Dialect缓存
	 */
	private Hashtable<String, Dialect> dialectCache = new Hashtable<String, Dialect>();
	

	private String pageSqlId = ".*ListByPage"; // 被拦截的请求方法
	
	
	private Dialect getDialect(){
		Dialect dialect = null; 
		 try {
			dialect = DialectFactory.getInstance().getDialect();
		} catch (Exception e) {
			if(properties!=null && properties.containsKey(dialectClassCandName)){
				try {
					//
					String dialectClass = properties.getProperty(dialectClassCandName);
					//增加DIALECT缓存
					if(!dialectCache.containsKey(dialectClass)){
						Class<?> clazz = ClassUtils.getClass(dialectClass);
						Dialect dialectN = (Dialect) clazz.newInstance();	
						dialectCache.put(dialectClass, dialectN);
					}
					dialect = dialectCache.get(dialectClass);
				} catch (Exception ex) {
					log.warn("init dialect from Interceptor properties:", ex);
				} 
			}
		}
		return dialect;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		
		
		BoundSql boundSql = statementHandler.getBoundSql(); 
		
		//MYBATIS 3 以上 版本 接口发生变化,以兼容接口调用 ReflectorFactory
//		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY, DEFAULT_REFLECTOR_FACTORY);
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
		
		boolean pagination = false, scalarQuery = false;
		ASimpleFilter filter = null;
		RowBounds rowBounds = null;
		//判断是否需要分页
		if(boundSql.getParameterObject()!=null && boundSql.getParameterObject() instanceof ASimpleFilter){
			filter = (ASimpleFilter)boundSql.getParameterObject();
			//如果设定了分页元数据,则执行分页
			if(filter.getPageSchema()!=null){
				rowBounds = (RowBounds) metaStatementHandler.getValue("delegate.rowBounds");
				rowBounds = pushRowBounds(filter, rowBounds);
				pagination = true;
			}else if(filter.getOpMode().compareTo(TargetOpMode.SELECT) == 0){
				scalarQuery = filter.isScalar();
			}
		}

		//如果需要分页
		if(pagination){
			//Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
			Dialect dialect = getDialect();
			if(dialect!=null){
				String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
				metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getPageSql(filter, originalSql, rowBounds.getOffset(), rowBounds.getLimit()));
				metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
				metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
				if (log.isDebugEnabled()) {
					log.debug("Page SQL : " + boundSql.getSql());
				}
			}else{
				log.error("!!! db dialect not inited, pagination interceptor skipped!");
			}
		
		}else if(scalarQuery){
			//单一返回结果
			Dialect dialect = getDialect();
			String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
			metaStatementHandler.setValue("delegate.boundSql.sql", dialect.getScalarSql(filter, originalSql));
			metaStatementHandler.setValue("delegate.rowBounds.offset", RowBounds.NO_ROW_OFFSET);
			metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT);
			if (log.isDebugEnabled()) {
				log.debug("Scalar SQL : " + boundSql.getSql());
			}
//			
//			metaStatementHandler.setValue("delegate.rowBounds.offset", 0);
//			metaStatementHandler.setValue("delegate.rowBounds.limit", 1);
		}else{
			//支持service原有模式的分页
			if(StringUtils.isNotBlank(pageSqlId)){
				BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper
						.getValueByFieldName(statementHandler, "delegate");
				if(delegate!=null){
					MappedStatement mappedStatement = (MappedStatement) ReflectHelper
							.getValueByFieldName(delegate, "mappedStatement");
					log.info("mappedStatement id:"+mappedStatement.getId());
					if (mappedStatement.getId().matches(getPageSqlId())) {
						Object parameterObject = boundSql.getParameterObject();
						if (parameterObject == null) {
							throw new NullPointerException("parameterObject error");
						} else {
							Connection connection = (Connection) invocation.getArgs()[0];
							String sql = boundSql.getSql();
							String countSql = "select count(0) from (" + sql+ ") myCount";
							log.debug("总数sql 语句:" + countSql);
							PreparedStatement countStmt = connection.prepareStatement(countSql);
							BoundSql countBS = new BoundSql(
									mappedStatement.getConfiguration(), countSql,
									boundSql.getParameterMappings(), parameterObject);
							setParameters(countStmt, mappedStatement, countBS,
									parameterObject);
							ResultSet rs = countStmt.executeQuery();
							int count = 0;
							if (rs.next()) {
								count = rs.getInt(1);
							}
							rs.close();
							countStmt.close();

							PageInfo page = null;
							if (parameterObject instanceof PageInfo) {
								page = (PageInfo) parameterObject;
								page.setTotalResult(count);
							} else if (parameterObject instanceof Map) {
								Map<String, Object> map = (Map<String, Object>) parameterObject;
								page = (PageInfo) map.get("page");
								if (page == null)
									page = new PageInfo();
								page.setTotalResult(count);
							} else {
								Field pageField = ReflectHelper.getFieldByFieldName(
										parameterObject, "page");
								if (pageField != null) {
									page = (PageInfo) ReflectHelper
											.getValueByFieldName(parameterObject,
													"page");
									if (page == null)
										page = new PageInfo();
									page.setTotalResult(count);
									ReflectHelper.setValueByFieldName(parameterObject,
											"page", page);
								} else {
									throw new NoSuchFieldException(parameterObject
											.getClass().getName());
								}
							}
							String pageSql = generatePageSql(sql, page);
							log.debug("page sql:" + pageSql);
							ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql);
						}
					}
				}
			}
		}
		
		return invocation.proceed();
	}
	

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/**
	 * 属性配置
	 */
	private Properties properties;
	
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void setParameters(PreparedStatement ps,
			MappedStatement mappedStatement, BoundSql boundSql,
			Object parameterObject) throws SQLException {
		ErrorContext.instance().activity("setting parameters")
				.object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql
				.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration
					.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null
					: configuration.newMetaObject(parameterObject);
			for (int i = 0; i < parameterMappings.size(); i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry
							.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName
							.startsWith(ForEachSqlNode.ITEM_PREFIX)
							&& boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value)
									.getValue(
											propertyName.substring(prop
													.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject
								.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException(
								"There was no TypeHandler found for parameter "
										+ propertyName + " of statement "
										+ mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value,
							parameterMapping.getJdbcType());
				}
			}
		}
	}
	
	/**
	 * 
	 * 方法描述：拼接sql语句，加入限制页数
	 * 
	 * @param sql
	 *            sql语句
	 * @param page
	 *            分页对象
	 * @return 新的sql语句
	 */
	private String generatePageSql(String sql, PageInfo page) {
		if (page != null) {
			StringBuffer pageSql = new StringBuffer();
			pageSql.append(sql);
			pageSql.append(" limit ").append(page.getOffSet()).append(",")
					.append(page.getPageSize());
			return pageSql.toString();
		} else {
			return sql;
		}
	}

	public String getPageSqlId() {
		return pageSqlId;
	}

	public void setPageSqlId(String pageSqlId) {
		this.pageSqlId = pageSqlId;
	}
}
