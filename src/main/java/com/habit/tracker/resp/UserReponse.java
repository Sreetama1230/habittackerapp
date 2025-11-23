package com.habit.tracker.resp;

import java.util.List;

import com.habit.tracker.model.User;


public class UserReponse {

	private Long id;
	private String displayName;
	private String username;

	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	
	public UserReponse(Long id, String displayName, String username) {
		super();
		this.id = id;
		this.displayName = displayName;
		this.username = username;
	}
	public UserReponse() {
		super();
	}
	
	public static UserReponse convertToUserReponse(User u) {
		UserReponse ur = new UserReponse(u.getId(), u.getDisplayName(), u.getUsername());
		return ur;
	}
	
	
}
