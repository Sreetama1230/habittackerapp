package com.habit.tracker.dto;

import java.time.LocalDateTime;

import com.habit.tracker.model.Habit;
import com.habit.tracker.model.HabitLog;
import com.habit.tracker.model.User;

public class HabitLogDTO {

	private String note;

	public HabitLogDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HabitLogDTO(String note) {
		super();
		this.note = note;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public static HabitLog convertToHabitLog(HabitLogDTO dto, User user, Habit habit) {
		HabitLog newHabitLog = new HabitLog(habit, user, LocalDateTime.now(), dto.getNote());
		return newHabitLog;
	}
}
