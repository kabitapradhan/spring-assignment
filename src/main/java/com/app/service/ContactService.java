package com.app.service;

import java.util.List;

import com.app.payload.ContactDto;

public interface ContactService {
	
	// create Contact
	public ContactDto createContact(ContactDto dto);
	// get all Contact
	public List<ContactDto> getAllContact();
	public ContactDto getContactById(int id);
	public ContactDto updateContact(ContactDto dto ,int id);
	//public ContactDto updateContactWithUser(ContactDto dto , int id);
	public void deleteContact(int id);
	public List<ContactDto> getContactByUser(int id);
}
