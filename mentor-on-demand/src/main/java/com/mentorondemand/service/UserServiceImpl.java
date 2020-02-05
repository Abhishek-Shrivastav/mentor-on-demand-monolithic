package com.mentorondemand.service;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mentorondemand.dao.UserDaoImpl;
import com.mentorondemand.entity.User;
import com.mentorondemand.entity.UserList;
import com.mentorondemand.facade.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDaoImpl dao;
	
	@Override
	public UserList getAll() {
		
		UserList list = new UserList(this.dao.getAllUser());
		
		return list;
	}

	@Override
	public User getById(Integer id) {
		
		return this.dao.getUserById(id);
	}

	@Override
	public boolean save(User user) {
		
		return this.dao.saveUser(user);
	}

	@Override
	public boolean update(User user) {
		
		return this.dao.updateUser(user);
	}

	@Override
	public boolean delete(Integer id) {
		
		return this.dao.deleteUser(id);
	}

	@Override
	public UserList getUserByTechId(Integer id) {
		
		UserList list = new UserList(this.dao.getUserByTechId(id));

		return list;
	}

	@Override
	public User getUserLoginDetail(String username, String password) throws NoResultException {
		
		return this.dao.getUserLoginDetail(username, password);
	}

	@Override
	public boolean getActiveStatus(Integer userId, Integer activate) {
		
		return this.dao.getActiveStatus(userId, activate);
	}

	@Override
	public boolean editUser(User user) {
		
		return this.dao.editUser(user);
	}
}
