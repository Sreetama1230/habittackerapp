package com.habit.tracker.resp;

import java.time.LocalDateTime;

import com.habit.tracker.model.HabitLog;

public class HabitLogResponse {
	private Long id;
	private LocalDateTime createdAt;
	private String note;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public HabitLogResponse(Long id, LocalDateTime createdAt, String note) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.note = note;
	}
	public HabitLogResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public static HabitLogResponse convertToHabitLogResponse(HabitLog hl) {
		return new HabitLogResponse(hl.getId(),hl.getCreatedAt(),hl.getNote());
	}
}
