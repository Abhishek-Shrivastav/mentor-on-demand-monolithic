package com.mentorondemand.entity;

import java.util.List;

public class UserList {

	private List<User> usetList;

	public UserList(List<User> usetList) {
		
		this.usetList = usetList;
	}

	public List<User> getUserList() {
		return usetList;
	}
}
