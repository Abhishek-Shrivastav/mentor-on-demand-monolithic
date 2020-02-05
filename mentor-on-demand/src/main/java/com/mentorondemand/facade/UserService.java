package com.mentorondemand.facade;

import com.mentorondemand.entity.User;
import com.mentorondemand.entity.UserList;

public interface UserService {

	UserList getAll();
	User getById(Integer id);
	boolean save(User user);
	boolean update(User user);
	boolean delete(Integer id);
	UserList getUserByTechId(Integer id);
	User getUserLoginDetail(String username,String password);
	boolean getActiveStatus(Integer userId,Integer activate);
	boolean editUser(User user);
}
