package com.wangmeng.sys.legacy.model;

import java.util.List;

import com.wangmeng.model.AbstractSerializable;
import com.wangmeng.sys.legacy.domain.SysPower;

public class SysPowerListModel extends AbstractSerializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8675585078494832268L;

	private SysPower rootPower;
	
	private List<SysPower> listPower;

	public SysPower getRootPower() {
		return rootPower;
	}

	public void setRootPower(SysPower rootPower) {
		this.rootPower = rootPower;
	}

	public List<SysPower> getListPower() {
		return listPower;
	}

	public void setListPower(List<SysPower> listPower) {
		this.listPower = listPower;
	}
	
	
}
