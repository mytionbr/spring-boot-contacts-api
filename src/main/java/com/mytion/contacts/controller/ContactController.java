package com.mytion.contacts.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mytion.contacts.model.Contact;
import com.mytion.contacts.repository.ContactRepository;
import com.mytion.contacts.service.ContactService;

@RestController
@RequestMapping({"/contacts"})
public class ContactController {
	
	private ContactService service;
	
	ContactController(ContactService contactService){
		this.service = contactService;
	}
	
	@GetMapping
	public List<Contact> findAll(){
		return service.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Contact> findById(@PathVariable Long id){
		return service.findById(id)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Contact create(@RequestBody Contact contact) {
		return service.save(contact);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Contact> update(@PathVariable("id") Long id,
										  @RequestBody Contact contact){
		return service.findById(id)
				.map(record -> { return ResponseEntity.ok().body(record);})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path = {"/{id}"})
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		return service.delete(id)
				.map(record ->{
					return ResponseEntity.ok().build();
				}).orElse(ResponseEntity.notFound().build());
	}
}
