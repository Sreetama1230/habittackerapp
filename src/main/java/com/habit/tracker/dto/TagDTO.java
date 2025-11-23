package com.habit.tracker.dto;
import com.habit.tracker.model.Tag;

public class TagDTO {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TagDTO(String name) {
		super();
		this.name = name;
	}
	
	public static Tag convertToTag(TagDTO dto) {
		Tag t = new Tag();
		t.setName(dto.getName());
		return t;
	}
}
