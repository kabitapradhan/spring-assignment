package com.app.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Contact;
import com.app.entity.Post;
import com.app.payload.ContactDto;
import com.app.payload.PostDto;
import com.app.repositories.ContactRepository;
import com.app.service.ContactService;

@Service
public class ContactserviceImpl implements ContactService {
	
	@Autowired
	private ContactRepository contactRepo;
	@Autowired 
	private ModelMapper mapper;

	@Override
	public ContactDto createContact(ContactDto dto) {
		Contact con = this.mapper.map(dto, Contact.class);
		Contact contact = this.contactRepo.save(con);
		return this.mapper.map(contact, ContactDto.class);
	}

	@Override
	public List<ContactDto> getAllContact() {
		List<Contact> list = this.contactRepo.findAll();
		List<ContactDto> all = list.stream()
				.map(mp-> this.mapper.map(mp, ContactDto.class))
				.collect(Collectors.toList());
		return all;
	}

	@Override
	public ContactDto getContactById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContactDto updateContact(ContactDto dto, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteContact(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ContactDto> getContactByUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
