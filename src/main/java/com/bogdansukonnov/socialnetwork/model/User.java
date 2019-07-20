package com.bogdansukonnov.socialnetwork.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Users")
public class User {
	
	@Id
	@GenericGenerator(name="inc", strategy="increment")
	@GeneratedValue(generator="inc")
	@Column(name="id")
	private Integer id;
	
	@Column(name="userName")
	private String userName;
	
	@Column(name="password")
	private String password;
	
	
	public User() {
		super();
	}
	
	public User(Integer id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}
	

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

}
