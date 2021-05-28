package com.mytion.contacts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mytion.contacts.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

}
