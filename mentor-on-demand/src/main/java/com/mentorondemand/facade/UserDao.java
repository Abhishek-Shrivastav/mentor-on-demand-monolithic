package com.mentorondemand.facade;

import java.util.List;

import com.mentorondemand.entity.User;

public interface UserDao {

	List<User> getAllUser();
	User getUserById(Integer id);
	boolean saveUser(User user);
	boolean updateUser(User user);
	boolean deleteUser(Integer id);
	List<User> getUserByTechId(Integer id);
	User getUserLoginDetail(String username,String password);
	boolean getActiveStatus(Integer userId,Integer activate);
	boolean editUser(User user);
	boolean findUserByUsername(String username);
	User findByUsername(String username);
	User findByContact(String contact);
}
