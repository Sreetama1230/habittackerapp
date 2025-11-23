package com.habit.tracker.dto;

import com.habit.tracker.model.User;

public class UserDTO {

	private String username;

	private String displayName;

	public UserDTO(String username, String displayName) {
		super();
		this.username = username;
		this.displayName = displayName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	
	public static User convertToUser(UserDTO dto) {
		User u  = new User();
		u.setUsername(dto.getUsername());
		if(dto.getDisplayName() == null) {
			u.setDisplayName(dto.getUsername());
		}else {
			u.setDisplayName(dto.getDisplayName());
		}
		
		return u;
	}
}
