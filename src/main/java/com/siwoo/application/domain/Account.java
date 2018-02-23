package com.siwoo.application.domain;



import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @ToString
public class Account {

    private String accountNo;
    private BigDecimal balance;
    private LocalDate lastPaidOn;

    public Account(String accountNo, BigDecimal balance, LocalDate lastPaidOn) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.lastPaidOn = lastPaidOn;
    }


}
