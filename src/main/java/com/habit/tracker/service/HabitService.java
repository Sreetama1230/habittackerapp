package com.habit.tracker.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habit.tracker.dto.HabitCategoryDTO;
import com.habit.tracker.dto.HabitDTO;
import com.habit.tracker.dto.HabitLogDTO;
import com.habit.tracker.dto.TagDTO;
import com.habit.tracker.exception.ForbiddenError;
import com.habit.tracker.exception.ResourceNotFoundException;
import com.habit.tracker.model.Habit;
import com.habit.tracker.model.HabitCategory;
import com.habit.tracker.model.HabitLog;
import com.habit.tracker.model.Tag;
import com.habit.tracker.model.User;
import com.habit.tracker.repo.HabitCategoryRepository;
import com.habit.tracker.repo.HabitLogRepository;
import com.habit.tracker.repo.HabitRepository;
import com.habit.tracker.repo.TagRepository;
import com.habit.tracker.repo.UserRepository;
import com.habit.tracker.resp.HabitResponse;

import jakarta.transaction.Transactional;

@Service
public class HabitService {

	@Autowired
	private HabitRepository habitRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private HabitCategoryRepository categoryRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private HabitCategoryService categoryService;

	@Autowired
	private TagService tagService;

	// userId will be in path variable
	@Transactional
	public Habit addHabit(HabitDTO dto, Long userId) {
		// user should be created

		User u = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found!"));

		Habit newHabit = HabitDTO.convertToHabit(dto);
		newHabit.setOwner(u);

		Optional<HabitCategory> hc = categoryRepository.findByName(dto.getCategory().getName());
		if (hc.isPresent()) {
			newHabit.setCategory(hc.get());
		} else {
			HabitCategory newHabitCategory = categoryService.addCategory(dto.getCategory());
			newHabit.setCategory(newHabitCategory);
		}

		Set<Tag> tags = new HashSet<>();

		List<String> listTagNames = dto.getTags().stream().map(t -> t.getName()).toList(); // user provided tags

		List<Tag> existingTags = tagRepository.findAllByNameIn(listTagNames); // records of matching tags

		Map<String, Tag> mapTags = existingTags.stream().collect(Collectors.toMap(t -> t.getName(), t -> t));

		for (TagDTO tagDTO : dto.getTags()) {

			Tag tag = mapTags.get(tagDTO.getName());

			if (tag != null) { // present in db
				tags.add(tag);
			} else {
				tags.add(tagService.addTag(tagDTO)); // new tag save to db and then add to the list
			}
		}
		newHabit.setTags(tags);
		newHabit.setLogs(new ArrayList<>());
		return habitRepository.save(newHabit);
	}

	// mark the habit , update the habit
	@Transactional
	public Habit markHabit(Long habitId, Long userId, HabitLogDTO dto) {

		if (habitRepository.findById(habitId).isPresent() && userRepository.findById(userId).isPresent()) {
			Habit dbHabit = habitRepository.findById(habitId).get();
			User dbUser = userRepository.findById(userId).get();
			if (dbHabit.getOwner().getId() == dbUser.getId()) {
				List<HabitLog> exisitngHabitLog = dbHabit.getLogs();
				HabitLog newHabitLog = HabitLogDTO.convertToHabitLog(dto, dbUser, dbHabit);
				if (exisitngHabitLog.size() > 0 && exisitngHabitLog.get(exisitngHabitLog.size() - 1).getCreatedAt()
						.toLocalDate().equals(LocalDateTime.now().toLocalDate())) {
					// if same date ...take the latest time and remove the previous time
					exisitngHabitLog.remove(exisitngHabitLog.size() - 1);

				}

				exisitngHabitLog.add(newHabitLog);
				habitRepository.save(dbHabit);

				return dbHabit;
			} else {
				throw new ForbiddenError("you are not the correct user to modify this habit");
			}
		} else {
			throw new ResourceNotFoundException("either user or habit is not found!");
		}

	}

	// put
	@Transactional
	public Habit updateHabit(HabitDTO dto, Long habitId, Long userId) {

		Habit dbHabit = habitRepository.findById(habitId)
				.orElseThrow(() -> new ResourceNotFoundException("no habit found!"));

		if (dbHabit.getOwner().getId().equals(userId)) {
			dbHabit.setTitle(dto.getTitle());
			dbHabit.setDescription(dto.getDescription());
			Set<TagDTO> dtotags = dto.getTags();

			// new set of tags will be added
			dbHabit.getTags().clear();

			Set<Tag> newTags = new TreeSet<>();

			List<String> listTagNames = dto.getTags().stream().map(t -> t.getName()).collect(Collectors.toList());

			List<Tag> existingTags = tagRepository.findAllByNameIn(listTagNames);

			Map<String, Tag> mapTags = existingTags.stream().collect(Collectors.toMap(t -> t.getName(), t -> t));

			for (TagDTO td : dtotags) {

				Tag t = mapTags.get(td.getName());

				if (t != null) {
					newTags.add(t);
				} else {
					newTags.add(TagDTO.convertToTag(td));
				}
			}

			HabitCategory hc = HabitCategoryDTO.convertToHabitCategory(dto.getCategory());

			if (!hc.getName().equals(dbHabit.getCategory().getName())) {
				dbHabit.setCategory(hc);
			}
			dbHabit.setTags(newTags);
			return habitRepository.save(dbHabit);

		} else {
			throw new ForbiddenError("you are not the correct user to update this habit");
		}

	}

	// patch
	@Transactional
	public Habit addHabitTags(HabitDTO dto, Long habitId, Long userId) {

		Habit dbHabit = habitRepository.findById(habitId)
				.orElseThrow(() -> new ResourceNotFoundException("no habit found!"));

		if (dbHabit.getOwner().getId().equals(userId)) {
			if (dto.getTitle() != null) {
				dbHabit.setTitle(dto.getTitle());
			}

			if (dto.getDescription() != null) {
				dbHabit.setDescription(dto.getDescription());
			}

			Set<Tag> dbTags = dbHabit.getTags();

			// adding the new ones
			List<String> listTagNames = dto.getTags().stream().map(t -> t.getName()).collect(Collectors.toList());

			List<Tag> existingTags = tagRepository.findAllByNameIn(listTagNames);

			Map<String, Tag> mapTags = existingTags.stream().collect(Collectors.toMap(t -> t.getName(), t -> t));

			for (TagDTO td : dto.getTags()) {

				Tag t = mapTags.get(td.getName());

				if (t != null) {
					dbTags.add(t);
				} else {
					dbTags.add(TagDTO.convertToTag(td));
				}
			}

			if (dto.getTags() != null) {
				Set<TagDTO> dtotags = dto.getTags();
				for (TagDTO td : dtotags) {
					Tag tag = new Tag();
					Optional<Tag> t = tagRepository.findByName(td.getName());
					if (!t.isPresent()) {
						tag = TagDTO.convertToTag(td);
					} else {
						tag = t.get();
					}
					dbTags.add(tag);
				}
			}

			dbHabit.setTags(dbTags);
			if (dto.getCategory() != null) {
				HabitCategory hc = HabitCategoryDTO.convertToHabitCategory(dto.getCategory());
				if (!hc.getName().equals(dbHabit.getCategory().getName())) {
					dbHabit.setCategory(hc);
				}
			}

			return habitRepository.save(dbHabit);
		} else {
			throw new ForbiddenError("you are not the correct user to update this habit");
		}

	}

	@Transactional
	public List<Habit> listHabits(Long userId) {

		User dbUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("no user found!"));

		return dbUser.getHabits();

	}

	@Transactional
	public HabitResponse deleteHabis(Long userId, Long habitId) {

		Habit dbHabit = habitRepository.findById(habitId)
				.orElseThrow(() -> new ResourceNotFoundException("no habit found!"));

		User dbUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("no user found!"));

		if (dbHabit.getOwner().getId().equals(userId)) {
			dbUser.getHabits().remove(dbHabit);
			HabitResponse response = HabitResponse.convertToHabitResponse(dbHabit);
			habitRepository.deleteById(habitId);
			return response;
		} else {
			throw new ForbiddenError("you are not allowed to delete any other user's habit");
		}
	}

}
