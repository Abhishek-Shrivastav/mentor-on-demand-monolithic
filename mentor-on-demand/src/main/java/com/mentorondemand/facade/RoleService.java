package com.mentorondemand.facade;

import com.mentorondemand.entity.Role;
import com.mentorondemand.entity.RoleList;

public interface RoleService {

	RoleList getAll();
	Role getById(Integer id);
	boolean save(Role role);
	boolean update(Role role);
	boolean delete(Integer id);
}
