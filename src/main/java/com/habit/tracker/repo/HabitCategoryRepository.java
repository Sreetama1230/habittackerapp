package com.habit.tracker.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habit.tracker.model.HabitCategory;

@Repository
public interface HabitCategoryRepository extends JpaRepository<HabitCategory, Long> {

	public Optional<HabitCategory> findByName(String name);
}
