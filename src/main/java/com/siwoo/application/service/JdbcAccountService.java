package com.siwoo.application.service;

import com.siwoo.application.domain.Account;
import com.siwoo.application.repository.AccountRepsotiry;
import com.siwoo.application.repository.CsvAccountRepository;
import com.siwoo.application.repository.JdbcAccountRepository;
import oracle.jdbc.pool.OracleDataSource;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class JdbcAccountService implements AccountService{
    private AccountRepsotiry accountRepsotiry;

    public JdbcAccountService(){
        /*try(InputStream inputStream = new ClassPathResource("META-INF/app_properties/dataSource.properties").getInputStream()) {
            Properties properties = new Properties();
            properties.load(inputStream);

            OracleDataSource oracleDataSource = new OracleDataSource();
            oracleDataSource.setURL((String) properties.get("dataSource.url"));
            oracleDataSource.setUser((String) properties.get("dataSource.user"));
            oracleDataSource.setPassword((String) properties.get("dataSource.password"));

            JdbcAccountRepository accountRepsotiry = new JdbcAccountRepository();
            accountRepsotiry.setDataSource(oracleDataSource);
            this.accountRepsotiry = accountRepsotiry;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }*/
    }

    public void setAccountRepsotiry(AccountRepsotiry accountRepsotiry) {
        this.accountRepsotiry = accountRepsotiry;
    }

    public AccountRepsotiry getAccountRepsotiry() {
        return accountRepsotiry;
    }

    public List<Account> findDelinquentAccounts() throws Exception {
        if(! (accountRepsotiry instanceof CsvAccountRepository) ){
            throw new UnsupportedOperationException();
        }
        return ((CsvAccountRepository) accountRepsotiry).findDeliquentAccounts();
    }


}
