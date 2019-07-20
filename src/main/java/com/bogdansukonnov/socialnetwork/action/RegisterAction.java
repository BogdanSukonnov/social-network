package com.bogdansukonnov.socialnetwork.action;

import org.apache.commons.lang3.StringUtils;

import com.bogdansukonnov.socialnetwork.dao.UserDAO;
import com.bogdansukonnov.socialnetwork.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
	
	private User user;
	

	@Override
	public void validate() {
		if (StringUtils.isEmpty(user.getUserName())) {
			addFieldError("user.userName", "User name can't be empty!");
			return;
		}
		
		if (user.getUserName().length() > 64) {
			addFieldError("user.userName", "User name is too long!");
		}
		
		UserDAO dao = new UserDAO();
		
		if (!dao.getUserByName(user.getUserName()).isEmpty()) {
			addFieldError("user.userName", "User is already exists!");
			return;
		}
		
		dao.close();
	}
		
	@Override
	public String execute() {
		UserDAO dao = new UserDAO();
		dao.insertUser(user);
		dao.close();
		return SUCCESS;
	}
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
