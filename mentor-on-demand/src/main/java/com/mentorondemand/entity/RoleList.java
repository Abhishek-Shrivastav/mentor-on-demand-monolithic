package com.mentorondemand.entity;

import java.util.List;

public class RoleList {

	private List<Role> roleList;

	public RoleList(List<Role> roleList) {
		
		this.roleList = roleList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}
}
