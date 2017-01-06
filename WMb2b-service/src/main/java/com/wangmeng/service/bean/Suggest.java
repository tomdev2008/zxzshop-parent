package com.wangmeng.service.bean;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import com.wangmeng.model.AbstractSerializable;
/**
 * 反馈意见
 * <ul>
 * <li>
  * <p>
 * @author 陈春磊 [2016-10-15上午11:20:56]<br/>
 * 新建
 * </p>
 * <b>修改历史：</b><br/>
 * </li>
 * </ul>
 */
@Alias("Suggest")
public class Suggest extends AbstractSerializable {

	private int id;//意见ID
	private Date createTime;//时间
	private String mobile="";//联系电话
	private String content="";//反馈内容
	private String suggestType="";//反馈类型
	/**
	 * @BareFieldName : id
	 *
	 * @return  the id
	 */
	
	public int getId() {
		return id;
	}
	/**
	 * @BareFieldName : id
	 *
	 * @return  the id
	 *
	 * @param id the id to set
	 */
	
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @BareFieldName : createTime
	 *
	 * @return  the createTime
	 */
	
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @BareFieldName : createTime
	 *
	 * @return  the createTime
	 *
	 * @param createTime the createTime to set
	 */
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @BareFieldName : mobile
	 *
	 * @return  the mobile
	 */
	
	public String getMobile() {
		return mobile;
	}
	/**
	 * @BareFieldName : mobile
	 *
	 * @return  the mobile
	 *
	 * @param mobile the mobile to set
	 */
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * @BareFieldName : content
	 *
	 * @return  the content
	 */
	
	public String getContent() {
		return content;
	}
	/**
	 * @BareFieldName : content
	 *
	 * @return  the content
	 *
	 * @param content the content to set
	 */
	
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @BareFieldName : suggestType
	 *
	 * @return  the suggestType
	 */
	
	public String getSuggestType() {
		return suggestType;
	}
	/**
	 * @BareFieldName : suggestType
	 *
	 * @return  the suggestType
	 *
	 * @param suggestType the suggestType to set
	 */
	
	public void setSuggestType(String suggestType) {
		this.suggestType = suggestType;
	}
	
	
}
