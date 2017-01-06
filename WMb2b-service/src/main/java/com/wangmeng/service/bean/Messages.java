package com.wangmeng.service.bean;

import java.io.Serializable;
import java.util.Date;

public class Messages implements Serializable {

	private static final long serialVersionUID = -8324981390200141345L;
	private int Id; // 主键
	private int UserId;// 用户id
	private String Message;// 消息
	private String IsRead;// 是否已读 0 未读，1已读
	private String UserType;// 用户类型 0买家，1卖家
	private Date CreateDate;// 创建时间
	private Date UpdateDate;// 修改时间
	private int LinkType;// 链接调转类型

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getIsRead() {
		return IsRead;
	}

	public void setIsRead(String isRead) {
		IsRead = isRead;
	}

	public String getUserType() {
		return UserType;
	}

	public void setUserType(String userType) {
		UserType = userType;
	}

	public Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}

	public Date getUpdateDate() {
		return UpdateDate;
	}

	public void setUpdateDate(Date updateDate) {
		UpdateDate = updateDate;
	}

	public int getLinkType() {
		return LinkType;
	}

	public void setLinkType(int linkType) {
		LinkType = linkType;
	}

}
