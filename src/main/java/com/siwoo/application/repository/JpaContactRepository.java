package com.siwoo.application.repository;

import com.siwoo.application.domain.Contact;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class JpaContactRepository extends AbstractJPARepository<Contact> implements ContactRepository{

    @Override
    public List<Contact> findByEmail(String email) {
        return entityManager.createNamedQuery("findContactsByEmail")
                .setParameter("email",email)
                .getResultList();
    }
}
