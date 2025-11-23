package com.habit.tracker.dto;

import com.habit.tracker.model.HabitCategory;

public class HabitCategoryDTO {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HabitCategoryDTO(String name) {
		super();
		this.name = name;
	}
	
	public static HabitCategory convertToHabitCategory(HabitCategoryDTO dto) {
		HabitCategory c = new HabitCategory();
		c.setName(dto.getName());
		return c;
	}
}
