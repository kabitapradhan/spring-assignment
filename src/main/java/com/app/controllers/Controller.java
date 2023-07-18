package com.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payload.ApiResponse;
import com.app.payload.LoginRequest;
import com.app.payload.LoginResponse;
import com.app.payload.UserDto;
import com.app.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class Controller {
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String mainController() {
		return "This is Fisrst For testing";
	}
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest logindata ) {
		UserDto dto = this.userService.doLogin(logindata);
		LoginResponse res = new LoginResponse();
		if(dto != null) {
			res.setResult(true);
			res.setUser(dto);
		} else {
			res.setResult(false);
			res.setUser(null);
		}
		return  new ResponseEntity<LoginResponse>(res , HttpStatus.OK);
	}

}
