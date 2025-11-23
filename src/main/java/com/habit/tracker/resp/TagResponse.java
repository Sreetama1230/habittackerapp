package com.habit.tracker.resp;

import com.habit.tracker.model.Tag;

public class TagResponse {

	private String name;
	private Long id;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}


	public TagResponse() {
		super();
	}
	
	public TagResponse(String name, Long id) {
		super();
		this.name = name;
		this.id = id;
	}

	public static TagResponse convertToTagResponse(Tag tag) {
		return new TagResponse(tag.getName() , tag.getId());
	}
	
}
