package com.wangmeng.query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： ABaseDaoFilter          	  <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月10日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  基础MYBATIS操作条件
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public abstract class ABaseDaoFilter<T> extends ASimpleFilter implements Cloneable {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1539942291994498656L;

	/**
     * 或
     */
    public abstract void or(T criteria);

    /**
     * 或
     */
    public abstract T or();

    /**
     * 创建条件
     */
    public abstract T createCriteria();

    /**
     * 清除
     */
    public abstract void clear();
	
    /**
     * 取得条件
     */
	public abstract List<T> getOredCriteria(); 
	
    /**
     * 取得第一个已经存在的条件
     * 如果不存在就创建一个
     * @return
     */
	public abstract T getScalarExistedCriteria();
	
	/**
	 * 获取的单个字段的名称
	 */
	private String singleFieldName;
	
	public String getSingleFieldName() {
		return singleFieldName;
	}

	public void setSingleFieldName(String singleFieldName) {
		this.singleFieldName = singleFieldName;
	}

	/**
	 * 获取的单个字段的类型
	 */
	private String singleFieldType;
 

	public String getSingleFieldType() {
		return singleFieldType;
	}

	public void setSingleFieldType(String singleFieldType) {
		this.singleFieldType = singleFieldType;
	}

	/**
	 * 扩展属性
	 *   用于指定某些特殊常量、配置常量，避免 mapper 硬编码
	 */
	private Map<String, Object> extProperties;

	public Map<String, Object> getExtProperties() {
		return extProperties;
	}

	public void setExtProperties(Map<String, Object> extProperties) {
		this.extProperties = extProperties;
	}
	
	/**
	 * 增加扩展属性
	 * 
	 * @param key
	 * @param value
	 */
	public void addExtProp(String key, Object value) {
		if (extProperties == null) {
			extProperties = new HashMap<String, Object>();
		}
		extProperties.put(key, value);
	}
	
	/**
	 * 增加扩展属性
	 * 
	 * @param map
	 */
	public void addExtProp(Map<String, Object> map) {
		if (map!=null) {
			if (extProperties == null) {
				extProperties = new HashMap<String, Object>();
			}
			extProperties.putAll(map);
		}
	}
	
	/**
	 * 参数绑定
	 * 
	 * @author ykd
	 *
	 */
	public static class ParaBind {
		
		private String paraName;

		private Object paraValue;

		public String getParaName() {
			return paraName;
		}

		public void setParaName(String paraName) {
			this.paraName = paraName;
		}

		public Object getParaValue() {
			return paraValue;
		}

		public void setParaValue(Object paraValue) {
			this.paraValue = paraValue;
		}

		public ParaBind(String paraName, Object paraValue) {
			super();
			this.paraName = paraName;
			this.paraValue = paraValue;
		}

	}

	/**
	 * Criterion
	 */
	public static class Criterion  implements java.io.Serializable {

		/**
		 * serialVersionUID
		 */
		private static final long serialVersionUID = 3589840735873362797L;

		private String condition;

		private Object value;

		private Object secondValue;

		private boolean noValue;

		private boolean singleValue;

		private boolean betweenValue;

		private boolean listValue;

		private String typeHandler;
		
//		private List<ParaBind> clauseBinds;
//		
//		public void addBind(String paraName, Object paraValue){
//			if(clauseBinds == null){
//				clauseBinds = new ArrayList<ParaBind>();
//			}
//			clauseBinds.add(new ParaBind(paraName, paraValue));
//		}

		public String getCondition() {
			return condition;
		}

		public Object getValue() {
			return value;
		}

		public Object getSecondValue() {
			return secondValue;
		}

		public boolean isNoValue() {
			return noValue;
		}

		public boolean isSingleValue() {
			return singleValue;
		}

		public boolean isBetweenValue() {
			return betweenValue;
		}

		public boolean isListValue() {
			return listValue;
		} 

		public String getTypeHandler() {
			return typeHandler;
		}
		
//		public List<ParaBind> getClauseBinds() {
//			return clauseBinds;
//		}
//
//		public void setClauseBinds(List<ParaBind> clauseBinds) {
//			this.clauseBinds = clauseBinds;
//		}

		public Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}
		
//		public Criterion(String condition, List<ParaBind> clauseBinds) {
//			super();
//			this.condition = condition;
//			this.typeHandler = null;
//			this.clauseBinds = clauseBinds;
//			//this.noValue = true;
//		}

		public Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value!=null && (!value.getClass().isPrimitive()) && (value instanceof Iterable<?> || value.getClass().isArray())) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}

		public Criterion(String condition, Object value) {
			this(condition, value, null);
		}

		public Criterion(String condition, Object value, Object secondValue,
				String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}

		public Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}

}
