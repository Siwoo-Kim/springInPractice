package com.siwoo.application.repository;

import com.siwoo.application.domain.Account;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
                String[] fields = line.split(",");
                Account account = new Account(
                        fields[0],
                        new BigDecimal(fields[1]),
                        LocalDate.parse(fields[2],dateTimeFormatter));
                accounts.add(account);
            }
            return accounts;
        }
    }

    public List<Account> findDeliquentAccounts() throws Exception {
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);

        Predicate<Account> isDeliquentAccount = account -> {
            boolean owesMoney = account.getBalance().compareTo(new BigDecimal(BigInteger.ZERO)) < 0;
            boolean thirtyDateLate = account.getLastPaidOn().compareTo(thirtyDaysAgo) <= 0;
            return owesMoney && thirtyDateLate;
        };

        return findAll()
                .stream()
                .filter(isDeliquentAccount).collect(Collectors.toList());
    }
}
