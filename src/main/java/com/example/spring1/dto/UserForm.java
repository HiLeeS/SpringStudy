package com.example.spring1.dto;

import com.example.spring1.entity.User;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class UserForm {
    private String userId;
    private String userPassword;
    private String userName;
    private int userBirth;
    private String userDepartment;
    private String userHabit;

    public User toEntity() {
        return new User(null, userId, userPassword, userName, userBirth, userDepartment, userHabit);
    }

}
