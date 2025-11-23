package com.habit.tracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.habit.tracker.dto.UserDTO;
import com.habit.tracker.model.User;
import com.habit.tracker.repo.UserRepository;
import com.habit.tracker.resp.UserReponse;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Transactional
	public User addUser(UserDTO dto) {
		User u = UserDTO.convertToUser(dto);
		u.setHabits(new ArrayList<>());
		u.setLogs(new ArrayList<>());
		return repository.save(u);
	}
	
	@Transactional
	public  List<UserReponse> getUsers(){
		 List<UserReponse>  resp =
				 repository.findAll().stream().map(u -> UserReponse.convertToUserReponse(u)).collect(Collectors.toList());
		return resp;
	}
}
