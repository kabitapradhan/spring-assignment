package com.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class Controller {
	
	@GetMapping("/")
	public String mainController() {
		return "This is Fisrst For testing";
	}

}
