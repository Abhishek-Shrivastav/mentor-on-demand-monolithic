package com.mentorondemand.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mentorondemand.entity.User;
import com.mentorondemand.facade.UserDao;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager entity;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUser() {
		
		List<User> user = this.entity.createQuery("From User").getResultList();
		
		return user;
	}

	@Override
	public User getUserById(Integer id) {
		
		User user = this.entity.find(User.class,id);
		
		return user;
	}

	@Override
	public boolean saveUser(User user) {
		System.out.println("User inputs : "+user);
		
		this.entity.persist(user);
		
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		
		User userUpd = this.entity.find(User.class,user.getId());
		
		userUpd.setUserName(user.getUserName());
		userUpd.setPassword(user.getPassword());
		userUpd.setFirstName(user.getFirstName());
		userUpd.setLastName(user.getLastName());
		userUpd.setContact(user.getContact());
		userUpd.setRoleId(user.getRoleId());
		userUpd.setLinkedInUrl(user.getLinkedInUrl());
		userUpd.setYearOfExp(user.getYearOfExp());
		userUpd.setIsActive(user.getIsActive());
		
		this.entity.flush();
		
		return true;
	}

	@Override
	public boolean deleteUser(Integer id) {
		
		User user = this.entity.find(User.class,id);
		this.entity.remove(user);
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByTechId(Integer id) {
		
		//User user = this.entity.find(User.class,id);
		List<User> user = this.entity.createQuery("From User Where id="+id).getResultList();
		
		return user;
	}

	@Override
	public User getUserLoginDetail(String username, String password) throws NoResultException {
		
		User user = (User) this.entity.createQuery("From User Where userName='"+username+"' And password='"+password+"' And isActive=1").getSingleResult();
		System.out.println("User : "+user);
		return user;
	}

	@Override
	public boolean getActiveStatus(Integer userId, Integer activate) {
		
		User userUpd = this.entity.find(User.class,userId);
		
		userUpd.setIsActive(activate);
		
		this.entity.flush();
		
		return true;
	}

	@Override
	public boolean editUser(User user) {
		
		System.out.println(user);
		
		User userUpd = this.entity.find(User.class,user.getId());
		
		userUpd.setFirstName(user.getFirstName());
		userUpd.setLastName(user.getLastName());
		userUpd.setContact(user.getContact());
		userUpd.setLinkedInUrl(user.getLinkedInUrl());
		userUpd.setYearOfExp(user.getYearOfExp());
		
		this.entity.flush();
		
		return true;
	}
}
