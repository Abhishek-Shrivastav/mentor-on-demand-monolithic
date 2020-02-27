package com.mentorondemand.facade;

import java.util.Collection;

import com.mentorondemand.entity.User;
import com.mentorondemand.entity.UserList;

public interface UserService {

	UserList getAll();
	User getById(Integer id);
	boolean save(User user,Collection<? extends Integer> set);
	boolean update(User user);
	boolean delete(Integer id);
	UserList getUserByTechId(Integer id);
	User getUserLoginDetail(String username,String password);
	boolean getActiveStatus(Integer userId,Integer activate);
	boolean editUser(User user);
	boolean findUserByUsername(String username);
	User findByUsername(String username);
	User findByContact(String contact);
}
