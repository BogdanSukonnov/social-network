package com.bogdansukonnov.socialnetwork.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.bogdansukonnov.socialnetwork.dao.UserDAO;
import com.bogdansukonnov.socialnetwork.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	private Map<String, Object> userSession;
	 
	@Override	
	public void validate() {
				
		if (StringUtils.isEmpty((user.getUserName()))) {
			addFieldError("user.userName", "Username can't be empty!");
			return;
		}
				
		UserDAO dao = new UserDAO();
		List<User> users = dao.getUserByName(user.getUserName());
		
		if (users.isEmpty()) {
			addFieldError("user.userName", "No user found!");
			return;
		}
		
		if (!users.get(0).getPassword().equals(user.getPassword())) {
			addFieldError("user.password", "Incorrect password!");
			return;
		}
		
		this.user = users.get(0);
		userSession.put("user", this.user);
		dao.close();
		
	}
	
	@Override
	public String execute() {
		System.out.println(user.getUserName());
		System.out.println(user.getPassword());
		
		return SUCCESS;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Map<String, Object> getUserSession() {
		return userSession;
	}

	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}	
	
}
