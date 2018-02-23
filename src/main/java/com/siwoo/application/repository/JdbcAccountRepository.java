package com.siwoo.application.repository;

import com.siwoo.application.domain.Account;
import oracle.jdbc.pool.OracleDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class JdbcAccountRepository implements AccountRepsotiry {
    private DataSource dataSource;

    public JdbcAccountRepository() throws SQLException {
/*
        OracleDataSource oracleDataSource = new OracleDataSource();
        oracleDataSource.setURL("jdbc:oracle:thin://localhost:1521/orcl");
        oracleDataSource.setPassword("java");
        oracleDataSource.setUser("java");
        dataSource = oracleDataSource;
*/
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public List<Account> findAll() throws Exception {
        return null;
    }
}
