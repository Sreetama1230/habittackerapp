package com.habit.tracker.model;

import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Tag  implements Comparable<Tag>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, unique = true)
	private String name;
	@ManyToMany(mappedBy = "tags")
	private Set<Habit> habits;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Habit> getHabits() {
		return habits;
	}
	public void setHabits(Set<Habit> habits) {
		this.habits = habits;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Tag(String name, Set<Habit> habits) {
		super();
		this.name = name;
		this.habits = habits;
	}
	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tag other = (Tag) obj;
		return Objects.equals(name, other.name);
	}
	@Override
	public int compareTo(Tag another) {
		return this.getName().compareTo(another.getName());
	}
	
	
	
	
}
