package com.siwoo.application.repository;

import com.siwoo.application.domain.Account;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvAccountRepository implements AccountRepsotiry{

    private Resource csvResource;

    /*Setter Injection*/
    public void setCsvResource(Resource csvResource) {
        this.csvResource = csvResource;
    }

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMddyyyy");
    @Override
    public List<Account> findAll() throws Exception {
        List<Account> accounts = new ArrayList<>();

        try(FileReader fileReader = new FileReader(csvResource.getFile());
            BufferedReader bufferedReader = new BufferedReader(fileReader)
        ){
            String line = null;
            while((line=bufferedReader.readLine()) != null){
                String[] fileds = line.split(",");
                Account account = new Account(
                        fileds[0],
                        new BigDecimal(fileds[1]),
                        LocalDateTime.parse(fileds[2],dateTimeFormatter));
                accounts.add(account);
            }
            return accounts;
        }
    }
}
