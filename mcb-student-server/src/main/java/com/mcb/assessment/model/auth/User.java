package com.mcb.assessment.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	private String username;
	@Column
	@JsonIgnore
	private String password;
	@Column
	@NotBlank
	@NotNull
	@Pattern(regexp = "(^(.+)@(\\S+)$)", message = "Invalid email provided")

	private String email;
	@Column
	@Pattern(regexp = "(^\\d{10}$)", message = "Invalid phone no.It should be 10 digit")
	private String phone;
	@Column
	@JsonIgnore
	private String businessTitle;
	@Column(name = "account_non_locked")
	@JsonIgnore
	private boolean accountNonLocked = true;
	@Column(name = "failed_attempt")
	private int failedAttempt;
	@Column(name = "lock_time")
	private Date lockTime;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLES",
			joinColumns = {
					@JoinColumn(name = "USER_ID")
			},
			inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID")})
	private Set<Role> roles;


	public boolean isAccountNonLocked () {
		return accountNonLocked;
	}

	public void setAccountNonLocked (boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public int getFailedAttempt () {
		return failedAttempt;
	}

	public void setFailedAttempt (int failedAttempt) {
		this.failedAttempt = failedAttempt;
	}

	public Date getLockTime () {
		return lockTime;
	}

	public void setLockTime (Date lockTime) {
		this.lockTime = lockTime;
	}

	public long getId () {
		return id;
	}

	public void setId (long id) {
		this.id = id;
	}

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

	public Set<Role> getRoles () {
		return roles;
	}

	public void setRoles (Set<Role> roles) {
		this.roles = roles;
	}
}