package com.example.spring1.dto;

import com.example.spring1.entity.User;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class UserForm {
    private String email;
    private String password;
    private String name;
    private String birth;
    private String major;
    private String hobby;

    public User toEntity() {
        return new User(null, email, password, name, birth, major, hobby);
    }

}
