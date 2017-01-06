package com.wangmeng.filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <ul>
 * <li>
 * <p>
 * 系统名程　　　　　： 浙江网盟B2B平台项目           <br/>
 * 子系统名称　　　　： 系统                 		  <br/>
 * 类／接口名　　　　： FriendlyException          <br/>
 * 版本信息　　　　　： 1.00                       <br/>
 * 新建日期　　　　　： 2016年10月7日               <br/>
 * 作者　　　　　　　： 衣奎德                      <br/>
 * <!-- <b>修改历史（修改者）：</b> -->			  <br/>
 * 
 *  查询条件
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class XCriterion implements java.io.Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7303551202241860034L;


	public XCriterion() {
		clauseSeqs = new ArrayList<XClause>();
	} 
	
	/**
	 * 是否有扩展属性
	 * 
	 * @return
	 */
	public boolean hasExtProperties(){
		return extProperties!=null && !extProperties.isEmpty();
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
	 * XUID
	 */
	private String xuid;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 条件名称空间约束
	 * 如为一个类名称或别名
	 */
	private String schema;
	
	/**
	 * 条件项
	 * 该处条件项只做并连条件项记录
	 */
	private List<XClause> clauseSeqs;
	
	
	public String getXuid() {
		return xuid;
	}

	public void setXuid(String xuid) {
		this.xuid = xuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public List<XClause> getClauseSeqs() {
		return clauseSeqs;
	}

	public void setClauseSeqs(List<XClause> clauseSeqs) {
		this.clauseSeqs = clauseSeqs;
	}

	/**
	 * 增加条件
	 * @param clause
	 */
	public void addClause(XClause clause){
		if(clause!=null && !clauseSeqs.contains(clause)){
			clauseSeqs.add(clause);
		}
	}
	
	/**
	 * 根据名称删除条件
	 * @param clauseName
	 */
	public void removeClauseByName(String clauseName){
		for(XClause clause : clauseSeqs){
			if(clause.getObj().equals(clauseName)){
				clauseSeqs.remove(clause);
				break;
			}
		}
	}
	
	/**
	 * 删除条件
	 * 
	 * @param clause
	 */
	public void removeClause(XClause clause){
		if(clauseSeqs.contains(clause)){
			clauseSeqs.remove(clause);
		}
	}
	
	/**
	 * 清除所有条件
	 */
	public void clearClause(){
		clauseSeqs.clear();
	}

	@Override
	public String toString() {
		return "XCriterion [xuid=" + xuid + ", name=" + name + ", schema="
				+ schema + ", clauseSeqs=" + clauseSeqs + "]";
	}
	
	public static XCriterion newInstance(){
		return new XCriterion();
	}
	
}
