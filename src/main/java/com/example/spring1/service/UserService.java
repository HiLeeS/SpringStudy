package com.example.spring1.service;


import com.example.spring1.dto.UserForm;
import com.example.spring1.entity.User;
import com.example.spring1.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service    //서비스 선언    (서비스 객체를 스프링 부트에 선언)
public class UserService {
    @Autowired  //DI
    private UserRepository userRepository;

    public User create(UserForm dto) {
        User user = dto.toEntity();
        if(user.getId() != null){
            return null;
        }
        return userRepository.save(user);
    }



}
