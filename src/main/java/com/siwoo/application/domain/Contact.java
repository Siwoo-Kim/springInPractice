package com.siwoo.application.domain;

import com.sun.istack.internal.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity @Table(name="contact")
@NamedQuery(
        name="findContactsByEmail",
        query = "select c from Contact c where c.email = :email")
@Getter @Setter @ToString
public class Contact {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="contact_id")
    private Long id;

    @NotNull
    @Length(min=1,max=40)
    @Column(name="last_name")
    private String lastName;

    @NotNull
    @Length(min=1,max=40)
    @Column(name="first_name")
    private String firstName;

    @Length(max = 1)
    @Column(name="mi")
    private String middleInitial;

    @Email
    private String email;

    @Transient
    public String getFullName(){
        String fullName = lastName + ", " + firstName;
        if(!(middleInitial == null || "".equals(middleInitial.trim()) )){
            fullName += " " + middleInitial+ ".";
        }
        return fullName;
    }


}
