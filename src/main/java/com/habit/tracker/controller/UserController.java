package com.habit.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.habit.tracker.dto.UserDTO;
import com.habit.tracker.model.User;
import com.habit.tracker.resp.UserReponse;
import com.habit.tracker.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> createUser( @RequestBody  UserDTO udto) {
		return new  ResponseEntity<User>(userService.addUser(udto) , HttpStatus.CREATED);		
	}
	
	@GetMapping
	public ResponseEntity<List<UserReponse>> getUsers(){
		return new ResponseEntity<>(userService.getUsers() , HttpStatus.OK);
	}
	

}
