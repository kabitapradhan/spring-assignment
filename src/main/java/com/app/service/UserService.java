package com.app.service;

import java.util.List;

import com.app.entity.User;
import com.app.payload.LoginRequest;
import com.app.payload.UserDto;

public interface UserService {
	
	// create user
	public UserDto createUser(UserDto dto);
	// get all user
	public List<UserDto> getAllUser();
	public UserDto getUserById(int id);
	public UserDto updateUser(UserDto dto , int id);
	public void deleteUser(int id);
	
	// temporary login without spring security
	public UserDto doLogin(LoginRequest logindata);
	

}
