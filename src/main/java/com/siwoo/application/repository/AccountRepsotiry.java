package com.siwoo.application.repository;

import com.siwoo.application.domain.Account;

import java.util.List;

public interface AccountRepsotiry {

    List<Account> findAll() throws Exception;

}
