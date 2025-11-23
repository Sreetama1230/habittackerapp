package com.habit.tracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habit.tracker.model.Habit;

@Repository
public interface HabitRepository extends JpaRepository<Habit, Long> {

}
