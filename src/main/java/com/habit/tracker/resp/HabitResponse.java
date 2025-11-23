package com.habit.tracker.resp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import com.habit.tracker.model.Habit;

import jakarta.transaction.Transactional;


public class HabitResponse {

	private Long id;
	private String title;
	private String description;
	private UserReponse userReponse;
	private List<HabitLogResponse> habitLogResponses;
	private HabitCategoryResponse habitCategoryResponse;
	private Set<TagResponse> tagResponses;
	
	public Long getId() {
		return id;
	}
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
	public UserReponse getUserReponse() {
		return userReponse;
	}
	public void setUserReponse(UserReponse userReponse) {
		this.userReponse = userReponse;
	}
	public List<HabitLogResponse> getHabitLogResponses() {
		return habitLogResponses;
	}
	public void setHabitLogResponses(List<HabitLogResponse> habitLogResponses) {
		this.habitLogResponses = habitLogResponses;
	}
	public HabitCategoryResponse getHabitCategoryResponse() {
		return habitCategoryResponse;
	}
	public void setHabitCategoryResponse(HabitCategoryResponse habitCategoryResponse) {
		this.habitCategoryResponse = habitCategoryResponse;
	}
	public Set<TagResponse> getTagResponses() {
		return tagResponses;
	}
	public void setTagResponses(Set<TagResponse> tagResponses) {
		this.tagResponses = tagResponses;
	}
	public HabitResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HabitResponse(Long id, String title, String description, UserReponse userReponse,
			List<HabitLogResponse> habitLogResponses, HabitCategoryResponse habitCategoryResponse,
			Set<TagResponse> tagResponses) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.userReponse = userReponse;
		this.habitLogResponses = habitLogResponses;
		this.habitCategoryResponse = habitCategoryResponse;
		this.tagResponses = tagResponses;
	}
	
	
	public  static HabitResponse convertToHabitResponse(Habit h) {
		UserReponse userReponse = UserReponse.convertToUserReponse(h.getOwner());
		HabitCategoryResponse categoryResponse = HabitCategoryResponse.convertToHabitCategoryResponse(h.getCategory());
		List<HabitLogResponse> habitLogResponses = new ArrayList<>();
		Set<TagResponse> tagResponses = new TreeSet<>();
		
		if(! ( h.getLogs() == null || h.getLogs().isEmpty())) {
			habitLogResponses = h.getLogs().stream()
					.map( l -> HabitLogResponse.convertToHabitLogResponse(l)).toList();
		}
		if( ! ( h.getTags() == null || h.getTags().isEmpty()  )) {
			 tagResponses = h.getTags().stream().map( t -> TagResponse.convertToTagResponse(t)).collect(Collectors.toSet());
		}
		
		return new 
				HabitResponse(h.getId() , h.getTitle(),h.getDescription(),userReponse,habitLogResponses,categoryResponse,tagResponses);
	}
}
