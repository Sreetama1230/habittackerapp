package com.habit.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.habit.tracker.dto.HabitDTO;
import com.habit.tracker.dto.HabitLogDTO;
import com.habit.tracker.model.Habit;
import com.habit.tracker.resp.HabitResponse;
import com.habit.tracker.service.HabitService;

@RestController
@RequestMapping("/habits")
public class HabitController {

	@Autowired
	private HabitService habitService;

	@PostMapping("/user/{userId}")
	public ResponseEntity<HabitResponse> addHabit(@RequestBody HabitDTO dto, @PathVariable Long userId) {
		Habit newHabit = habitService.addHabit(dto, userId);

		return new ResponseEntity<HabitResponse>(HabitResponse.convertToHabitResponse(newHabit), HttpStatus.CREATED);
	}

	@PostMapping("/mark")
	public ResponseEntity<HabitResponse> markHabit(@RequestParam Long habit, @RequestParam Long user, @RequestBody HabitLogDTO hr) {

		Habit markedHabit = habitService.markHabit(habit,user,hr);

		return new ResponseEntity<HabitResponse>(HabitResponse.convertToHabitResponse(markedHabit), HttpStatus.OK);
	}

	@PutMapping("/{habitId}")
	public ResponseEntity<HabitResponse> updateHabit(@RequestBody HabitDTO dto, @PathVariable Long habitId, @RequestParam Long user) {

		Habit updateHabit = habitService.updateHabit(dto, habitId,user);

		return new ResponseEntity<HabitResponse>(HabitResponse.convertToHabitResponse(updateHabit), HttpStatus.OK);
	}

	@PatchMapping("/{habitId}")
	public ResponseEntity<HabitResponse> addHabitTags(@RequestBody HabitDTO dto, @PathVariable Long habitId,@RequestParam Long user) {
		Habit updateHabit = habitService.addHabitTags(dto, habitId,user);

		return new ResponseEntity<HabitResponse>(HabitResponse.convertToHabitResponse(updateHabit), HttpStatus.OK);

	}


	@GetMapping
	public ResponseEntity<List<HabitResponse>> listHabits(@RequestParam Long user) {
		List<Habit> lists = habitService.listHabits(user);
		List<HabitResponse> habitResponses = lists.stream().map(hr -> HabitResponse.convertToHabitResponse(hr))
				.toList();
		return new ResponseEntity<List<HabitResponse>>(habitResponses, HttpStatus.OK);
	}

	@DeleteMapping("{habitId}/users/{userId}")
	public ResponseEntity<HabitResponse> deleteHabis(@PathVariable Long userId, @PathVariable Long habitId) {
		return new ResponseEntity<HabitResponse>( habitService.deleteHabis(userId, habitId), HttpStatus.OK);
	}

}
