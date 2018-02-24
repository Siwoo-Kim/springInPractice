package com.siwoo.application.service;

import com.siwoo.application.domain.Contact;
import com.siwoo.application.service.ContactService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/app_config/jdbc-context.xml")
public class TestJdbcContactServiceImpl {

    @Autowired ContactService contactService;

    @Test
    public void testCreateContact() throws InterruptedException {
        Contact contact = new Contact();
        contact.setEmail("contact1@email.com");
        contact.setFirstName("Siwoo");
        contact.setLastName("Kim");
        contactService.createContact(contact);

        Contact foundContact = contactService.getContact(contact.getId());
        assertNotNull(foundContact);
        System.out.println(foundContact);

    }
}
