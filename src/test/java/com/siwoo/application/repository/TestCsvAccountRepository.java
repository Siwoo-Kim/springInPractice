package com.siwoo.application.repository;

import com.siwoo.application.domain.Account;
import com.siwoo.application.service.AccountService;
import com.siwoo.application.service.JdbcAccountService;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

@Log
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/app_config/app_csv_context.xml")
public class TestCsvAccountRepository {

    @Autowired AccountRepsotiry accountRepsotiry;
    @Autowired AccountService accountService;
    @Test
    public void testFindAll() throws Exception {
        assertNotNull(accountRepsotiry);
        accountRepsotiry.findAll().stream().map(Account::toString).forEach(log::warning);
    }

    @Test
    public void testDeliquentAccounts() throws Exception {
        ((CsvAccountRepository)accountRepsotiry).findDeliquentAccounts().stream().map(Account::toString)
                .forEach(log::warning);
        assertTrue(((CsvAccountRepository)accountRepsotiry).findDeliquentAccounts()
                .size() == 1);
    }

    @Test
    public void testFindDelinquentAccounts() throws Exception {

        for(Account account : ((JdbcAccountService)accountService).findDelinquentAccounts()){
            System.out.println(account);
        }
    }
}
