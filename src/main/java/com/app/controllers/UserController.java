package com.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payload.UserDto;
import com.app.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {
	@Autowired
	private UserService userService;
	
	// create or add user into database
	@PostMapping("/")
	public ResponseEntity<UserDto> addUser(@RequestBody UserDto dto ) {
		UserDto createUser = this.userService.createUser(dto);
		return new ResponseEntity<UserDto>(createUser,HttpStatus.CREATED);
	}
	// get all user from database
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUser() {
		List<UserDto> list = this.userService.getAllUser();
		return new ResponseEntity<List<UserDto>>(list,HttpStatus.CREATED);
	}
	
	

}
