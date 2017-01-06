package com.wangmeng.filter;

import java.util.ArrayList;
import java.util.List;

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
 *   条件项
 * 
 * Copyright (c) wangmeng  Co., Ltd. 2016. All rights reserved.
 * </p>
 *
 * </li>
 * </ul>
 */
public class XClause implements java.io.Serializable  {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7805446288195665161L;
	
	/**
	 * 原生sql条件
	 */
	private boolean natived;

	/**
	 * 上下文
	 */
	private Object ctx;
	
	/**
	 * 条件客体
	 */
	private String obj;
	
	/**
	 * 条件谓词
	 */
	private String verb;
	
	/**
	 * 条件描述
	 */
	private Object desc;
	
	/**
	 * 构造函数
	 * @param ctx
	 * @param obj
	 * @param verb
	 * @param desc
	 */
	public XClause(Object ctx, String obj, String verb, Object desc) {
		super();
		this.ctx = ctx;
		this.obj = obj;
		this.verb = verb!=null ? verb.toUpperCase() : verb;
		this.desc = desc;
	}


	/**
	 * 构造函数
	 * @param obj
	 * @param verb
	 * @param desc
	 */
	public XClause(String obj, String verb, Object desc) {
		super();
		this.obj = obj;
		this.verb = verb!=null ? verb.toUpperCase() : verb;
		this.desc = desc;
	}
	
	public XClause(Object ctx, String obj, CriterionVerb verb, Object desc) {
		super();
		this.ctx = ctx;
		this.obj = obj;
		this.verb = verb.name().toUpperCase();
		this.desc = desc;
	}

	public XClause(String obj, CriterionVerb verb, Object desc) {
		super();
		this.obj = obj;
		this.verb = verb.name().toUpperCase();
		this.desc = desc;
	}
	
//	public XClause(Object desc) {
//		super();
//		this.desc = desc;
//	}
	
	public XClause(String rootVerb) {
		this.rootVerb = rootVerb;
		this.setRoot(true);
	}
	
	public XClause() {
		super();
	}
	
	public Object getCtx() {
		return ctx;
	}

	public void setCtx(Object ctx) {
		this.ctx = ctx;
	}

	public String getObj() {
		return obj;
	}

	public void setObj(String obj) {
		this.obj = obj;
	}

	public String getVerb() {
		return verb;
	}

	public void setVerb(String verb) {
		this.verb = verb.toUpperCase();
	}

	public Object getDesc() {
		return desc;
	}

	public void setDesc(Object desc) {
		this.desc = desc;
	}
	
	/**
	 * 链接上一级条件谓词and/or
	 */
	private String rootVerb;
	
	public String getRootVerb() {
		return rootVerb;
	}
	
//	/**
//	 * 链接子条件谓词and/or
//	 */
//	private String childrenVerb;
//
//	public String getChildrenVerb() {
//		return childrenVerb;
//	}
//	
//	public void setChildrenVerb(String childrenVerb) {
//		this.childrenVerb = childrenVerb;
//	}

	/**
	 * 子条件
	 */
	private List<XClause> children;
	
	public List<XClause> getChildren() {
		return children;
	}

	
//	public XClause(String rootVerb, String childrenVerb) {
//		this.rootVerb = rootVerb;
//		this.childrenVerb = childrenVerb;
//		this.setRoot(true);
//	}
	
	public void addChild(XClause c){
		if(children == null){
			children = new ArrayList<XClause>();
		}
		children.add(c);
	}

	public boolean isNatived() {
		return natived;
	}

	public void setNatived(boolean natived) {
		this.natived = natived;
	}
	
	
	@Override
	public String toString() {
		return "XClause [ctx=" + ctx + ", obj=" + obj + ", verb=" + verb
				+ ", desc=" + desc + "]";
	}
	
	private boolean isRoot;
	
	public boolean isRoot() {
		return isRoot;
	}


	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public boolean hasChildren(){
		return isRoot && rootVerb!=null && children!=null && children.size()>0;
	}
	
	public static XClause newInstance(String rootVerb){
		return new XClause(rootVerb);
	}
	
	public static XClause newInstance(ClauseGroupVerb rootVerb){
		return new XClause(rootVerb.name());
	}
	
//	public static XClause newInstance(String rootVerb, String childrenVerb){
//		return new XClause(rootVerb, childrenVerb);
//	}
//	public static XClause newInstance(ClauseGroupVerb rootVerb, ClauseGroupVerb childrenVerb){
//		return new XClause(rootVerb.name(), childrenVerb.name());
//	}
	
	public static XClause newInstance(Object ctx, String obj, String verb, Object desc){
		return new XClause(ctx, obj, verb, desc);
	}
	
	public static XClause newInstance(String obj, String verb, Object desc){
		return new XClause(obj, verb, desc);
	}
	
	public static XClause newInstance(Object ctx, String obj, CriterionVerb verb, Object desc){
		return new XClause(ctx, obj, verb, desc);
	}
	
	public static XClause newInstance(String obj, CriterionVerb verb, Object desc){
		return new XClause(obj, verb, desc);
	}
	public static XClause newNativedInstance(String desc){
		XClause clause = new XClause();
		clause.setDesc(desc);
		clause.setNatived(true);
		return clause;
	}
	
}
