package com.bogdansukonnov.socialnetwork.action;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.bogdansukonnov.socialnetwork.dao.UserDAO;
import com.bogdansukonnov.socialnetwork.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class AddFriendAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;	
	private Map<String, Object> userSession;
	private String name;
	
	
	public void validate() {
		
		if (StringUtils.isEmpty(name)) {
			addFieldError("name", "Name can't be epmty!");
			return;
		}
				
		//check if user try to friend himself
		User currentUser = (User) userSession.get("user");		
		if (currentUser.getUserName().equals(name)) {
			addFieldError("name", "You can't add yourself!");
			return;
		}
		
		UserDAO dao = new UserDAO();
		
		//check if given name is already in friends
		for(User u : currentUser.getFriends()) {
			if (u.getUserName().equals(name)) {
				addFieldError("name", "Already your friend!");
				return;
			}
		}
		
		//find user in the database		
		List<User> users = dao.getUserByName(name);		
		if (users.isEmpty()) {
			addFieldError("name", "Can't find user!");
			return;
		}		
		
		dao.close();
		
	}
	
	public String execute() {
		UserDAO dao = new UserDAO();
		List<User> users = dao.getUserByName(name);
		User currentUser = (User) userSession.get("user");
		List<User> friends = currentUser.getFriends();
		friends.add(users.get(0));
		currentUser.setFriends(friends);
		dao.update(currentUser);
		dao.close();
		return SUCCESS;				
	}

	
	public Map<String, Object> getUserSession() {
		return userSession;
	}

	public void setUserSession(Map<String, Object> userSession) {
		this.userSession = userSession;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.userSession = session;		
	}
	
}
