package com.mentorondemand.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@NotNull(message = "First name not provide!")
	@Column(name = "first_name")
	private String firstName;
	@NotNull(message = "Lastname not provide!")
	@Column(name = "last_name")
	private String lastName;
	@Size(min = 10,max = 10,message = "Enter vaild contact number!")
	@Pattern(regexp = "[0-9]*")
	@NotNull(message = "Contact not provide!")
	@Column(name = "contact")
	private String contact;
	@Email
	@NotNull(message = "Linkedin id not provide!")
	@Column(name = "linkedin_url")
	private String linkedInUrl;
	@NotNull(message = "Expirence id not provide!")
	@Column(name = "year_of_exp")
	private Integer yearOfExp;
	
	public UserProfile() {}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
}
