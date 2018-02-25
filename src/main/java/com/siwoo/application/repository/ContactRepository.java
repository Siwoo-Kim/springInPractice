package com.siwoo.application.repository;

import com.siwoo.application.domain.Contact;

import java.util.List;

public interface ContactRepository extends Repository<Contact>{
    List<Contact> findByEmail(String email);
}
