package com.app.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.User;
import com.app.payload.LoginRequest;
import com.app.payload.UserDto;
import com.app.repositories.UserRepository;
import com.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDto createUser(UserDto dto) {
		User user = this.mapper.map(dto, User.class);
		User savedUser = this.userRepo.save(user);
		return this.mapper.map(savedUser, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> list = this.userRepo.findAll();
		List<UserDto> all = list.stream()
				.map(mp-> this.mapper.map(mp, UserDto.class))
				.collect(Collectors.toList());
		return all;
	}

	@Override
	public UserDto getUserById(int id) {
		User user = this.userRepo.findById(id).get();
		return this.mapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUser(UserDto dto, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserDto doLogin(LoginRequest logindata) {
		List<User> all = this.userRepo.findAll();
		User fuser = null;
		for(User user : all) {
			if(user.getPassword().equals(logindata.getPassword()) && 
					user.getEmail().equals(logindata.getUsername()) ) {
				fuser = user;
				break;
			}
		}
		if(fuser != null) {
			return this.mapper.map(fuser, UserDto.class);
		}
		return null;
	}

}
