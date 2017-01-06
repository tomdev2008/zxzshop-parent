package com.wangmeng.sys.legacy.model;

import com.wangmeng.model.AbstractSerializable;
import com.wangmeng.sys.legacy.domain.SysPower;

public class SysPowerModel extends AbstractSerializable  { 

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1461974721519569161L;

	private Long id;

    private String powerName;

    private String xuid;
    
    private String redirectUrl;

    private String superid;

    private Short sta;

    /**
     * 权限资源类型(0-功能菜单 1-页面按钮 2-数据资源)
     */
    private int sourceType;

    /**
     * 所属系统
     */
    private String owner;

    private String createTime;

    private String modifyTime;

    private String remark;

    private Integer display;
    
    private short checked;//1代表选中，0代表不选中
    
    private SysPower sysPower;//权限信息

    public SysPower getSysPower() {
		return sysPower;
	}

	public void setSysPower(SysPower sysPower) {
		this.sysPower = sysPower;
	}

	public short getChecked() {
		return checked;
	}

	public void setChecked(short checked) {
		this.checked = checked;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getXuid() {
		return xuid;
	}

	public void setXuid(String xuid) {
		this.xuid = xuid;
	}

	public String getPowerName() {
        return powerName;
    }

    public void setPowerName(String powerName) {
        this.powerName = powerName == null ? null : powerName.trim();
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl == null ? null : redirectUrl.trim();
    }

    public String getSuperid() {
        return superid;
    }

    public void setSuperid(String superid) {
        this.superid = superid == null ? null : superid.trim();
    }

    public Short getSta() {
        return sta;
    }

    public void setSta(Short sta) {
        this.sta = sta;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime == null ? null : modifyTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}