package com.siwoo.application.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static lombok.AccessLevel.PACKAGE;

@ToString @Getter @Setter
@NoArgsConstructor(access = PACKAGE)
public class Member {
    private String firstName;
    private String lastName;

    public Member(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
