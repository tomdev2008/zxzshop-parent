/*
 * @(#)SimpleFilePart.java 2016-10-13下午2:44:55
 * Copyright © 2004-2016 网盟. All rights reserved.
 */
package com.wangmeng.common.utils;

/**
 * 
 * <ul>
 * <li>
 * <p>
 * @author 宋愿明 [2016-10-13下午2:44:55]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */

public class SimpleFilePart {
	
	public SimpleFilePart() {
	}

	String fileFormName;
	byte[] file;
	String tempfileName;
	
	public String getFileFormName() {
		return fileFormName;
	}
	public void setFileFormName(String fileFormName) {
		this.fileFormName = fileFormName;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	public SimpleFilePart(String fileFormName, byte[] file, String tempfileName) {
		super();
		this.fileFormName = fileFormName;
		this.file = file;
		this.tempfileName = tempfileName;
	}
	public String getTempfileName() {
		return tempfileName;
	}
	public void setTempfileName(String tempfileName) {
		this.tempfileName = tempfileName;
	}
	
}
