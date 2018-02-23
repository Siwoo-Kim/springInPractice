package com.siwoo.application.repository;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class TestJdbcAccountRepository {

    @Test
    public void notNull() throws SQLException {
        JdbcAccountRepository accountRepository = new JdbcAccountRepository();
        assertNotNull(accountRepository);
    }
}
