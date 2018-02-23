package com.siwoo.application.service;

import com.siwoo.application.repository.JdbcAccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/app_config/app_dependency_injection.xml")
public class TestJdbcAccountService {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testNotNull(){
        /*
        AccountService accountService = new JdbcAccountService();
        assertNotNull(accountService);
        */

        AccountService accountService = applicationContext.getBean(AccountService.class);
        assertNotNull(accountService);
        assertNotNull(((JdbcAccountService)accountService).getAccountRepsotiry());
        assertNotNull(((JdbcAccountRepository)((JdbcAccountService)accountService).getAccountRepsotiry()).getDataSource());


    }
}
