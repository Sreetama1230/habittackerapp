package com.habit.tracker.dto;


import java.util.Set;
import java.util.stream.Collectors;

import com.habit.tracker.model.Habit;
import com.habit.tracker.model.HabitCategory;
import com.habit.tracker.model.Tag;


public class HabitDTO {
	
	private String title;
	private String description;
	private HabitCategoryDTO category;
	private Set<TagDTO> tags;
	
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public HabitCategoryDTO getCategory() {
		return category;
	}
	public void setCategory(HabitCategoryDTO category) {
		this.category = category;
	}
	public Set<TagDTO> getTags() {
		return tags;
	}
	public void setTags(Set<TagDTO> tags) {
		this.tags = tags;
	}
	public HabitDTO(String title, String description, HabitCategoryDTO category, Set<TagDTO> tags) {
		super();
		this.title = title;
		this.description = description;
		this.category = category;
		this.tags = tags;
	}
	
	public static Habit convertToHabit(HabitDTO dto) {
		Habit h = new Habit();
			h.setTitle(dto.getTitle());
			h.setDescription(dto.getDescription());
			Set<Tag> tags = dto.getTags().stream().map(d -> TagDTO.convertToTag(d)).collect(Collectors.toSet());
			h.setTags(tags);
			HabitCategory hc = HabitCategoryDTO.convertToHabitCategory(dto.getCategory());
			h.setCategory(hc);
		return h;
	}

}
