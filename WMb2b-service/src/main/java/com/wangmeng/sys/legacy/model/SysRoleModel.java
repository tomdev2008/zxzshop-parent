package com.wangmeng.sys.legacy.model;

import com.wangmeng.model.AbstractSerializable;

public class SysRoleModel extends AbstractSerializable  {

    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8646598760553998480L;

	private Long id;

    private String xuid;
    
    private String roleName;

    private String remark;

    private Short sta;
    
    private String powerIds;

    private String createTime;

    private short checked;
    
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

	public short getChecked() {
		return checked;
	}

	public void setChecked(short checked) {
		this.checked = checked;
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
