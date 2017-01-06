/*
 * @(#)InquirySheetPhoto.java 2016-11-1上午11:28:11
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.service.bean;

import com.wangmeng.model.AbstractSerializable;

/**
 * 询价单/采购单图片
 * 
 * <ul>
 * <li>
 * <p>
 * 
 * @author 宋愿明 [2016-11-1上午11:28:11]<br/>
 *         新建
 *         </p>
 *         <b>修改历史：</b><br/>
 *         </li>
 *         </ul>
 */

public class InquirySheetPhoto extends AbstractSerializable {

	private Integer Id;
	private String SheetCode;// 询价单或采购单号

	private String OrgPath;// 原图
	private String maxPath;// 大图
	private String minPath;// 小图
	private String dictCode;// 字典
	private String description;// 描述
	private String name;// 名字

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getSheetCode() {
		return SheetCode;
	}

	public void setSheetCode(String sheetCode) {
		SheetCode = sheetCode;
	}

	public String getOrgPath() {
		return OrgPath;
	}

	public void setOrgPath(String orgPath) {
		OrgPath = orgPath;
	}

	public String getMaxPath() {
		return maxPath;
	}

	public void setMaxPath(String maxPath) {
		this.maxPath = maxPath;
	}

	public String getMinPath() {
		return minPath;
	}

	public void setMinPath(String minPath) {
		this.minPath = minPath;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
