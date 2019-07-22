package com.bogdansukonnov.socialnetwork.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GenericGenerator(name = "inc", strategy = "increment")
	@GeneratedValue(generator = "inc")
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "userName")
	private String userName;
	
	@Column(name = "password")
	private String password;
	
	@OneToMany(fetch=FetchType.EAGER)
	private List<User> friends;
	
	//Join table create SQL:
	//create table Users_Users (User_id integer not null, friends_id integer not null, primary key (User_id, friends_id));
	
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

	@Transactional
	public List<User> getFriends() {
		return friends;
	}

	public void setFriends(List<User> friends) {
		this.friends = friends;
	}	

}
