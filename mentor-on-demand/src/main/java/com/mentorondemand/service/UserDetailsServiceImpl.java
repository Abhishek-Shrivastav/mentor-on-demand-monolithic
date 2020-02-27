package com.mentorondemand.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mentorondemand.entity.Role;
import com.mentorondemand.entity.User;
import com.mentorondemand.facade.UserDao;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		User user = this.userDao.findByUsername(username);
		
		if (user == null) {
            throw new UsernameNotFoundException("Could not find user with name '" + username + "'");
        }
		
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		for (Role role : user.getRole())
		{
        	grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
		
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
	}
}
