package com.habit.tracker.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.habit.tracker.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
