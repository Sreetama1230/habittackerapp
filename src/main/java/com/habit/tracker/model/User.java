package com.habit.tracker.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@Column
		private String username;
		@Column
		private String displayName;
		
		@OneToMany(mappedBy = "owner" , cascade = CascadeType.ALL)
		private List<Habit> habits;
		
		@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
		private List<HabitLog> logs;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getDisplayName() {
			return displayName;
		}

		public void setDisplayName(String displayName) {
			this.displayName = displayName;
		}

		public List<Habit> getHabits() {
			return habits;
		}

		public void setHabits(List<Habit> habits) {
			this.habits = habits;
		}

		public List<HabitLog> getLogs() {
			return logs;
		}

		public void setLogs(List<HabitLog> logs) {
			this.logs = logs;
		}

		public User(String username, String displayName, List<Habit> habits, List<HabitLog> logs) {
			super();
			this.username = username;
			this.displayName = displayName;
			this.habits = habits;
			this.logs = logs;
		}

		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
}
