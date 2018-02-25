package com.siwoo.application.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/app_config/orm-context.xml")
public class TestJpaContactServiceImpl {

    @Autowired ContactService contactService;

    @Test
    public void test(){
        assertNotNull(contactService);
    }
}
