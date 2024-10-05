package com.mcb.assessment.model.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

//TODO: Use MAP Struct
public class UserDto {

	private String username;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String email;
	private String phone;
	private String businessTitle;


	public String getUsername () {
		return username;
	}

	public void setUsername (String username) {
		this.username = username;
	}

	public String getPassword () {
		return password;
	}

	public void setPassword (String password) {
		this.password = password;
	}

	public String getEmail () {
		return email;
	}

	public void setEmail (String email) {
		this.email = email;
	}

	public String getPhone () {
		return phone;
	}

	public void setPhone (String phone) {
		this.phone = phone;
	}

	public String getBusinessTitle () {
		return businessTitle;
	}

	public void setBusinessTitle (String businessTitle) {
		this.businessTitle = businessTitle;
	}

	public User getUserFromDto () {
		User user = new User ();
		user.setUsername (username);
		user.setPassword (password);
		user.setEmail (email);
		user.setPhone (phone);
		user.setBusinessTitle (businessTitle);

		return user;
	}

}