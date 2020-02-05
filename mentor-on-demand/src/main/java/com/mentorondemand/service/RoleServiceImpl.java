package com.mentorondemand.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mentorondemand.entity.Role;
import com.mentorondemand.entity.RoleList;
import com.mentorondemand.facade.RoleDao;
import com.mentorondemand.facade.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao dao;
	
	public RoleList getAll() {
		
		RoleList list = new RoleList(this.dao.getAllRole());
		
		return list;
	}

	public Role getById(Integer id) {
		
		return this.dao.getRoleById(id);
	}

	public boolean save(Role role) {
		
		return this.dao.saveRole(role);
	}

	public boolean update(Role role) {
		
		return this.dao.updateRole(role);
	}

	public boolean delete(Integer id) {
		
		return this.dao.deleteRole(id);
	}
}
