package com.habit.tracker.resp;

import com.habit.tracker.model.HabitCategory;

public class HabitCategoryResponse {

	private String name;
	private Long id;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HabitCategoryResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HabitCategoryResponse(String name, Long id) {
		super();
		this.name = name;
		this.id = id;
	}


	public static HabitCategoryResponse convertToHabitCategoryResponse(HabitCategory hc) {
		return new HabitCategoryResponse(hc.getName() , hc.getId());
	}

	
	
}
