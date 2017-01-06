package com.wangmeng.web.sys.form;

public class SysRoleForm {
	
    private Long id;

    private String xuid;
    
    private String roleName;

    private String remark;

    private Short sta;
    
    private String powerIds;

    private String createTime;

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

	public String getPowerIds() {
		return powerIds;
	}

	public void setPowerIds(String powerIds) {
		this.powerIds = powerIds;
	}

	public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

}
