package com.siwoo.application.service;

import com.siwoo.application.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.HashMap;
import java.util.List;

public class JdbcContactServiceImpl implements ContactService{
    private static final String CREATE_SQL ="insert into contact (last_name, first_name, mi, email) " +
            "values (:lastName, :firstName, :mi, :email)";
    private static final String FIND_ALL_SQL = "select id, last_name, first_name, mi, email from contact";
    private static final String FIND_ALL_BY_EMAIL_LIKE_SQL = "select id, last_name, first_name, mi, email from contact " +
            "where email like :email";
    private static final String FIND_ONE_SQL = "select id, last_name, first_name, mi, email from contact " +
            "where id = :id";
    private static final String UPDATE_SQL = "update contact set last_name = :lastName, " +
            "first_name = :firstName, mi = :mi, email = :email where id = :id";
    private static final String DELETE_SQL = "delete from contact where id = :id";

    @Autowired private NamedParameterJdbcOperations jdbcTemplate;

    private RowMapper<Contact> contactRowMapper = ((resultSet, rowNum) -> {
        Contact contact = new Contact();
        contact.setId(resultSet.getLong(1));
        contact.setLastName(resultSet.getString(2));
        contact.setFirstName(resultSet.getString(3));
        contact.setMiddleInitial(resultSet.getString(4));
        contact.setEmail(resultSet.getString(5));
        return contact;

    });

    @Override
    public void createContact(Contact contact){
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("lastName",contact.getLastName())
                .addValue("firstName",contact.getFirstName())
                .addValue("mi",contact.getMiddleInitial())
                .addValue("email",contact.getEmail());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(CREATE_SQL,params,keyHolder);
        contact.setId(keyHolder.getKey().longValue());
    }

    @Override
    public List<Contact> getContacts(){
        return jdbcTemplate.query(FIND_ALL_SQL,new HashMap<String,Object>(),contactRowMapper);
    }

    @Override
    public List<Contact> getContactsByEmail(String email){
        SqlParameterSource params = new MapSqlParameterSource("email","%"+email+"%");
        return jdbcTemplate.query(FIND_ALL_BY_EMAIL_LIKE_SQL, params, contactRowMapper);
    }

    @Override
    public Contact getContact(Long id){
        SqlParameterSource params = new MapSqlParameterSource("id",id);
        return jdbcTemplate.queryForObject(FIND_ONE_SQL,params,contactRowMapper);
    }

    @Override
    public void updateContact(Contact contact){
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("lastName",contact.getLastName())
                .addValue("firstName",contact.getFirstName())
                .addValue("mi",contact.getMiddleInitial())
                .addValue("email",contact.getEmail());
        jdbcTemplate.update(UPDATE_SQL,params);
    }

    public void deleteContact(Long id){
        jdbcTemplate.update(DELETE_SQL,new MapSqlParameterSource("id",id));
    }


}






























