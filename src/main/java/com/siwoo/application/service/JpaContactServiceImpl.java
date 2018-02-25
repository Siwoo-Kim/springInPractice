package com.siwoo.application.service;

import com.siwoo.application.domain.Contact;
import com.siwoo.application.repository.ContactRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Properties;

@Service @Transactional
public class JpaContactServiceImpl implements ContactService{

    @Autowired ContactRepository contactRepository;
    @Override
    public void createContact(Contact contact) {
        contactRepository.create(contact);
    }

    @Override
    public List<Contact> getContacts() {
        return contactRepository.getAll();
    }

    @Override
    public List<Contact> getContactsByEmail(String email) {
        return contactRepository.findByEmail(email);
    }

    @Override
    public Contact getContact(Long id) {
        return contactRepository.get(id);
    }

    @Override
    public void updateContact(Contact contact) {
        contactRepository.update(contact);
    }

}
