package com.siwoo.application.service;

import com.siwoo.application.domain.Contact;

import java.util.List;

public interface ContactService {

    void createContact(Contact contact);

    List<Contact> getContacts();

    List<Contact> getContactsByEmail(String email);

    Contact getContact(Long id);

    void updateContact(Contact contact);
}
