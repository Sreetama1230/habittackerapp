package com.habit.tracker.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class HabitLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "habit_id")
	private Habit habit;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column
	private LocalDateTime createdAt;
	@Column
	private String note;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Habit getHabit() {
		return habit;
	}
	public void setHabit(Habit habit) {
		this.habit = habit;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public HabitLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HabitLog(Habit habit, User user, LocalDateTime createdAt, String note) {
		super();
		this.habit = habit;
		this.user = user;
		this.createdAt = createdAt;
		this.note = note;
	}
	
	
}
