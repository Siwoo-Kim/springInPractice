package com.siwoo.application.service;

import com.siwoo.application.domain.Contact;
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
public class OrmContactServiceImpl implements ContactService{

    @PersistenceContext private EntityManager entityManager;

    @Override
    public void createContact(Contact contact) {
        entityManager.persist(contact);
    }

    @Override
    public List<Contact> getContacts() {
        return entityManager.createQuery("select c from Contact c",Contact.class).getResultList();
    }

    @Override
    public List<Contact> getContactsByEmail(String email) {
        return entityManager.createNamedQuery("findContactsByEmail",Contact.class)
                .setParameter("email","%" + email + "%")
                .getResultList();
    }

    @Override
    public Contact getContact(Long id) {
        return entityManager.find(Contact.class,id);
    }

    @Override
    public void updateContact(Contact contact) {
        entityManager.remove(contact);
    }

}
