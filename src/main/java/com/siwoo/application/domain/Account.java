package com.siwoo.application.domain;



import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class Account {

    private String accountNo;
    private BigDecimal balance;
    private LocalDateTime lastPaidOn;

    public Account(String accountNo, BigDecimal balance, LocalDateTime lastPaidOn) {
        this.accountNo = accountNo;
        this.balance = balance;
        this.lastPaidOn = lastPaidOn;
    }


}
