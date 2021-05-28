package com.mytion.contacts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mytion.contacts.model.Contact;
import com.mytion.contacts.repository.ContactRepository;

@Service
public class ContactService {
	
	private ContactRepository repository;
	
	ContactService(ContactRepository contactRepository){
		this.repository = contactRepository;
	}
	
	public List<Contact> findAll(){
		return repository.findAll();
	}
	
	public Optional<Contact> findById(Long id) {
		return repository.findById(id);
	}
	
	public Contact save(Contact contact) {
		return repository.save(contact);
	}
	
	public Optional<Contact> update(Long id,Contact contact) {
		return repository.findById(id)
						.map(record ->{ 
						record.setName(contact.getName());
						record.setEmail(contact.getEmail());
						record.setPhone(contact.getPhone());
						Contact updated = repository.save(record);
						return updated;});			
	}
	
	public Optional<Contact> delete(Long id) {
		return repository.findById(id)
				.map(record ->{
					 repository.deleteById(id);
					 return record;
				});
	}
}
