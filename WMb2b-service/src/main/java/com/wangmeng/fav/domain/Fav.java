package com.wangmeng.fav.domain;

import java.sql.Timestamp;
import java.util.Date;

import com.wangmeng.model.AbstractSerializable;

/**
 *  收藏
 *   对应表： 
 *    wm_fav
 *
 * @mbggenerated
 */
public class Fav extends AbstractSerializable { 

	private static final long serialVersionUID = -233364317643455619L;

	/**
     * ID
     *  wm_fav.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * UUID
     *  wm_fav.xuid
     *
     * @mbggenerated
     */
    private String xuid;

    /**
     * 收藏名称
     *  wm_fav.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * 用户id
     *  wm_fav.user_id
     *
     * @mbggenerated
     */
    private Long userId;

    /**
     * 收藏目标id
     *  wm_fav.tid
     *
     * @mbggenerated
     */
    private Long tid;

    /**
     * 收藏目标类型
     *  wm_fav.ttype
     *
     * @mbggenerated
     */
    private Short ttype;

    /**
     * TAG
     *  wm_fav.tags
     *
     * @mbggenerated
     */
    private String tags;

    /**
     * 排序
     *  wm_fav.sort
     *
     * @mbggenerated
     */
    private Integer sort;

    /**
     * 备注
     *  wm_fav.description
     *
     * @mbggenerated
     */
    private String description;

    /**
     * 创建人
     *  wm_fav.create_by
     *
     * @mbggenerated
     */
    private Long createBy;

    /**
     * 创建时间
     *  wm_fav.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * 修改人
     *  wm_fav.update_by
     *
     * @mbggenerated
     */
    private Long updateBy;

    /**
     * 修改时间
     *  wm_fav.update_date
     *
     * @mbggenerated
     */
    private Date updateDate;

    /**
     * 状态（1可用，0不可用)
     *  wm_fav.status
     *
     * @mbggenerated
     */
    private Short status;

    /**
     * 获取 ID
     *  column： wm_fav.id
     *
     * @return the value of wm_fav.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置 ID
     *  column：  wm_fav.id
     *
     * @param id the value for wm_fav.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 UUID
     *  column： wm_fav.xuid
     *
     * @return the value of wm_fav.xuid
     *
     * @mbggenerated
     */
    public String getXuid() {
        return xuid;
    }

    /**
     * 设置 UUID
     *  column：  wm_fav.xuid
     *
     * @param xuid the value for wm_fav.xuid
     *
     * @mbggenerated
     */
    public void setXuid(String xuid) {
        this.xuid = xuid == null ? null : xuid.trim();
    }

    /**
     * 获取 收藏名称
     *  column： wm_fav.title
     *
     * @return the value of wm_fav.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置 收藏名称
     *  column：  wm_fav.title
     *
     * @param title the value for wm_fav.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取 用户id
     *  column： wm_fav.user_id
     *
     * @return the value of wm_fav.user_id
     *
     * @mbggenerated
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置 用户id
     *  column：  wm_fav.user_id
     *
     * @param userId the value for wm_fav.user_id
     *
     * @mbggenerated
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取 收藏目标id
     *  column： wm_fav.tid
     *
     * @return the value of wm_fav.tid
     *
     * @mbggenerated
     */
    public Long getTid() {
        return tid;
    }

    /**
     * 设置 收藏目标id
     *  column：  wm_fav.tid
     *
     * @param tid the value for wm_fav.tid
     *
     * @mbggenerated
     */
    public void setTid(Long tid) {
        this.tid = tid;
    }

    /**
     * 获取 收藏目标类型
     *  column： wm_fav.ttype
     *
     * @return the value of wm_fav.ttype
     *
     * @mbggenerated
     */
    public Short getTtype() {
        return ttype;
    }

    /**
     * 设置 收藏目标类型
     *  column：  wm_fav.ttype
     *
     * @param ttype the value for wm_fav.ttype
     *
     * @mbggenerated
     */
    public void setTtype(Short ttype) {
        this.ttype = ttype;
    }

    /**
     * 获取 TAG
     *  column： wm_fav.tags
     *
     * @return the value of wm_fav.tags
     *
     * @mbggenerated
     */
    public String getTags() {
        return tags;
    }

    /**
     * 设置 TAG
     *  column：  wm_fav.tags
     *
     * @param tags the value for wm_fav.tags
     *
     * @mbggenerated
     */
    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    /**
     * 获取 排序
     *  column： wm_fav.sort
     *
     * @return the value of wm_fav.sort
     *
     * @mbggenerated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置 排序
     *  column：  wm_fav.sort
     *
     * @param sort the value for wm_fav.sort
     *
     * @mbggenerated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取 备注
     *  column： wm_fav.description
     *
     * @return the value of wm_fav.description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置 备注
     *  column：  wm_fav.description
     *
     * @param description the value for wm_fav.description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取 创建人
     *  column： wm_fav.create_by
     *
     * @return the value of wm_fav.create_by
     *
     * @mbggenerated
     */
    public Long getCreateBy() {
        return createBy;
    }

    /**
     * 设置 创建人
     *  column：  wm_fav.create_by
     *
     * @param createBy the value for wm_fav.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

 
    /**
     * 设置 创建时间
     *  column：  wm_fav.create_date
     *
     * @param createDate the value for wm_fav.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取 修改人
     *  column： wm_fav.update_by
     *
     * @return the value of wm_fav.update_by
     *
     * @mbggenerated
     */
    public Long getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置 修改人
     *  column：  wm_fav.update_by
     *
     * @param updateBy the value for wm_fav.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

  

    /**
     * 设置 修改时间
     *  column：  wm_fav.update_date
     *
     * @param updateDate the value for wm_fav.update_date
     *
     * @mbggenerated
     */
    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取 状态（1可用，0不可用)
     *  column： wm_fav.status
     *
     * @return the value of wm_fav.status
     *
     * @mbggenerated
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 设置 状态（1可用，0不可用)
     *  column：  wm_fav.status
     *
     * @param status the value for wm_fav.status
     *
     * @mbggenerated
     */
    public void setStatus(Short status) {
        this.status = status;
    }

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
    
}