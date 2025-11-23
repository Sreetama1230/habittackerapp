package com.habit.tracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habit.tracker.model.HabitLog;

@Repository
public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {

}
