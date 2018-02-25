package com.siwoo.application.repository;

import com.siwoo.application.domain.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/app_config/orm-context.xml")
public class TestJpsContactRepository {

    @Autowired JpaContactRepository jpaContactRepository;

    @Test
    public void test(){
        Contact contact = new Contact();
        contact.setFirstName("Siwoo");
        contact.setLastName("Kim");
        contact.setEmail("sm123tt@email.com");

        jpaContactRepository.create(contact);

        Contact foundContact = jpaContactRepository.get(contact.getId());
        assertNotNull(foundContact);
    }
}
