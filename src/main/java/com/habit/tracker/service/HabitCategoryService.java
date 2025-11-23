package com.habit.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habit.tracker.dto.HabitCategoryDTO;
import com.habit.tracker.model.HabitCategory;
import com.habit.tracker.repo.HabitCategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class HabitCategoryService {

	@Autowired
	private HabitCategoryRepository categoryRepository;

	@Transactional
	public HabitCategory addCategory(HabitCategoryDTO categoryDTO) {

		HabitCategory hc = HabitCategoryDTO.convertToHabitCategory(categoryDTO);

		return categoryRepository.save(hc);
	}

}
