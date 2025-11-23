package com.habit.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habit.tracker.dto.HabitLogDTO;
import com.habit.tracker.model.Habit;
import com.habit.tracker.model.HabitLog;
import com.habit.tracker.model.User;
import com.habit.tracker.repo.HabitLogRepository;
import com.habit.tracker.repo.HabitRepository;
import com.habit.tracker.repo.UserRepository;
import com.habit.tracker.resp.HabitResponse;
import com.habit.tracker.resp.UserReponse;

import jakarta.transaction.Transactional;

@Service
public class HabitLogService {

	@Autowired
	private HabitLogRepository habitLogRepository;
	
	@Autowired
	private HabitRepository habitRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public HabitLog addHabitLog(HabitLogDTO  dto, Long habitId,Long userId) {
		Habit dbHabit = habitRepository.findById(habitId).get();
		User dbUser = userRepository.findById(userId).get();
		
		HabitLog log = HabitLogDTO.convertToHabitLog(dto,dbUser ,dbHabit  );
		return habitLogRepository.save(log);
	}
}
