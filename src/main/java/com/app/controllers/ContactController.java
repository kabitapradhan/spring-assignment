package com.app.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.payload.ApiResponse;
import com.app.payload.ContactDto;
import com.app.service.ContactService;

@RestController
@RequestMapping("/api/v1/contacts")
@CrossOrigin
public class ContactController {
	
	@Autowired
	private ContactService conatctService;
	@Autowired 
	private ModelMapper mapper;
	
	// create or add Contact into database
		@PostMapping("/")
		public ResponseEntity<ContactDto> addContact(@RequestBody ContactDto dto) {
			ContactDto createContact = this.conatctService.createContact(dto);
			return new ResponseEntity<ContactDto>(createContact,HttpStatus.CREATED);
		}
		// get all Contact from database
		@GetMapping("/")
		public ResponseEntity<List<ContactDto>> getAllContact() {
			List<ContactDto> list = this.conatctService.getAllContact();
			return new ResponseEntity<List<ContactDto>>(list,HttpStatus.OK);
		}
		@DeleteMapping("/{contactId}")
		public ResponseEntity<ApiResponse> deleteContact(@PathVariable("contactId") int contactId) {
			this.conatctService.deleteContact(contactId);
			ApiResponse api = new ApiResponse();
			api.setMessage("Delete successfull");
			api.setSuccess(true);
			return new ResponseEntity<ApiResponse>(api,HttpStatus.OK);
		}

}

















