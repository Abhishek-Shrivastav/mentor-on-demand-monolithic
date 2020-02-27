package com.mentorondemand.service;

import java.util.Collection;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mentorondemand.entity.User;
import com.mentorondemand.entity.UserList;
import com.mentorondemand.facade.RoleDao;
import com.mentorondemand.facade.UserDao;
import com.mentorondemand.facade.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
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
	public boolean save(User user,Collection<? extends Integer> set)
	{
		Integer ids = set.iterator().next();
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setConfirmPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole(this.roleDao.getById(ids));
		
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

	@Override
	public boolean findUserByUsername(String username) {

		return this.dao.findUserByUsername(username);
	}

	@Override
	public User findByUsername(String username)
	{
		return this.dao.findByUsername(username);
	}

	@Override
	public User findByContact(String contact) {
		
		return this.dao.findByContact(contact);
	}
}
