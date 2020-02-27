package com.mentorondemand.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class UserSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private SimpleAuthenticationSuccessHandler successHandler;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth
			.userDetailsService(this.userDetailsService)
	    	.passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
			.headers()
				.frameOptions().sameOrigin()
			.and()
			.authorizeRequests()
				.antMatchers("/signup").permitAll()
				.antMatchers("/").permitAll()
				.antMatchers("/mentor/**").access("hasRole('ROLE_MENTOR')")
				.antMatchers("/student/**").access("hasRole('ROLE_USER')")
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
			.and()
			.formLogin()
				.loginPage("/signin")
				.failureUrl("/signin?error=1")
				.successHandler(this.successHandler)
				.usernameParameter("username").passwordParameter("password")
			.and()
			.logout()
				.logoutSuccessUrl("/signin?logout=1");
	}
}
