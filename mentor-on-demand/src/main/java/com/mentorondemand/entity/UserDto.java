package com.mentorondemand.entity;

import java.util.Collection;

public class UserDto {

	private Integer id;
	private String userName;
	private String password;
	private String confirmPassword;
	private String firstName;
	private String lastName;
	private String contact;
	private String linkedInUrl;
	private Integer yearOfExp;
	private Integer isActive;
	private Collection<? extends Integer> roles;
	
	public UserDto() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	/*public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}*/

	public String getLinkedInUrl() {
		return linkedInUrl;
	}

	public void setLinkedInUrl(String linkedInUrl) {
		this.linkedInUrl = linkedInUrl;
	}

	public Integer getYearOfExp() {
		return yearOfExp;
	}

	public void setYearOfExp(Integer yearOfExp) {
		this.yearOfExp = yearOfExp;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Collection<? extends Integer> getRoles() {
		return roles;
	}

	public void setRoles(Collection<? extends Integer> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", contact=" + contact + ", linkedInUrl="
				+ linkedInUrl + ", yearOfExp=" + yearOfExp + ", isActive=" + isActive + "]";
	}
}
