package com.mentorondemand.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mentorondemand.entity.Role;
import com.mentorondemand.facade.RoleDao;

@Repository
@Transactional
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private EntityManager entity;
	
	public List<Role> getAllRole() {
		
		@SuppressWarnings("unchecked")
		List<Role> list = this.entity.createQuery("From Role").getResultList();
		
		return list;
	}

	public Role getRoleById(Integer id) {
		
		Role role = this.entity.find(Role.class,id);
		
		return role;
	}

	public boolean saveRole(Role role) {
		
		this.entity.persist(role);
		
		return true;
	}

	public boolean updateRole(Role role) {
		
		Role r = this.entity.find(Role.class,role.getId());
		
		r.setRoleName(role.getRoleName());
		
		this.entity.flush();
		
		return true;
	}

	public boolean deleteRole(Integer id) {
		
		Role role = this.entity.find(Role.class,id);
		this.entity.remove(role);
		
		return true;
	}

	@Override
	public List<Role> getById(Integer ids) {
		
		@SuppressWarnings("unchecked")
		List<Role> list = this.entity.createQuery("From Role Where id = "+ids).getResultList();
		
		return list;
	}
}
