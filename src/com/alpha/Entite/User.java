/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.Entite;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author br4in
 */
public class User {
	protected int id;
	protected String username;
	protected String email;
	protected Boolean enabled;
	protected String salt;
	protected String password;
	protected TimeZone last_login;
	protected String roles;
	protected String firstname;
	protected String lastname;

	public User(String username, String email, Boolean enabled, String salt, String password, TimeZone last_login, String roles, String firstname, String lastname) {
		this.username = username;
		this.email = email;
		this.enabled = enabled;
		this.salt = salt;
		this.password = password;
		this.last_login = last_login;
		this.roles = roles;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TimeZone getLast_login() {
		return last_login;
	}

	public void setLast_login(TimeZone last_login) {
		this.last_login = last_login;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
}
