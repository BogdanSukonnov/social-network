package com.bogdansukonnov.socialnetwork.action;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.bogdansukonnov.socialnetwork.dao.UserDAO;
import com.bogdansukonnov.socialnetwork.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;
	
	private User user;
	private Map<String, Object> userSession;	

	public Map<String, Object> getUserSession() {
		return userSession;
	}

	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}

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

	@Override
	public void setSession(Map<String, Object> session) {
		this.userSession = session;		
	}
}
