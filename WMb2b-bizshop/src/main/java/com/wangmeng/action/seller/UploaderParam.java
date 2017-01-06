package com.wangmeng.action.seller;

public class UploaderParam {
	private String name; //通过upfile获取    
	 private String originalName; //通过upfile获取    
	 private Long size; //通过upfile获取    
	 private String state;//成功必须为"SUCCESS"    
	 private String type; //通过upfile获取    
	 private String url; //图片回显url
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOriginalName() {
		return originalName;
	}
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	 
}
