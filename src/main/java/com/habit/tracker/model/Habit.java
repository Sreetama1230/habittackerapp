package com.habit.tracker.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Habit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String title;
	@Column
	private String description;

	@ManyToOne(optional = false)
	@JoinColumn(name = "owner_id")
	private User owner;
	
	@ManyToOne( cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id" )
	private HabitCategory category;
	
	@ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinTable(name = "habit_tags",
				joinColumns = @JoinColumn(name = "habit_id"),
				inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private Set<Tag> tags;
	
	@OneToMany(mappedBy = "habit" , cascade = CascadeType.ALL,orphanRemoval = true)
	private List<HabitLog> logs;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public HabitCategory getCategory() {
		return category;
	}

	public void setCategory(HabitCategory category) {
		this.category = category;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public List<HabitLog> getLogs() {
		return logs;
	}

	public void setLogs(List<HabitLog> logs) {
		this.logs = logs;
	}

	public Habit(String title, String description, User owner, HabitCategory category, Set<Tag> tags,
			List<HabitLog> logs) {
		super();
		this.title = title;
		this.description = description;
		this.owner = owner;
		this.category = category;
		this.tags = tags;
		this.logs = logs;
	}

	public Habit() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
