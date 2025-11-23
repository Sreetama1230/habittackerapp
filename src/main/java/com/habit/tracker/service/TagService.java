package com.habit.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habit.tracker.dto.TagDTO;
import com.habit.tracker.model.Tag;
import com.habit.tracker.repo.TagRepository;

import jakarta.transaction.Transactional;

@Service
public class TagService {

	@Autowired
	private TagRepository tagRepository;
	
	@Transactional
	public Tag addTag(TagDTO dto) {
	 return	tagRepository.save( TagDTO.convertToTag(dto));
	}
}
