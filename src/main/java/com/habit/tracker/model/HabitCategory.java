package com.habit.tracker.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class HabitCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@OneToMany(mappedBy = "category")
	private List<Habit> habits;
	
	
	public HabitCategory(String name, List<Habit> habits) {
		super();
		this.name = name;
		this.habits = habits;
	}
	public HabitCategory() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Habit> getHabits() {
		return habits;
	}
	public void setHabits(List<Habit> habits) {
		this.habits = habits;
	}
	
	
}
