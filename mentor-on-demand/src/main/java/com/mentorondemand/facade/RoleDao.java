package com.mentorondemand.facade;

import java.util.List;

import com.mentorondemand.entity.Role;

public interface RoleDao {

	List<Role> getAllRole();
	Role getRoleById(Integer id);
	boolean saveRole(Role role);
	boolean updateRole(Role role);
	boolean deleteRole(Integer id);
}
