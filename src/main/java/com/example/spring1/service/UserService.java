package com.example.spring1.service;


import com.example.spring1.dto.UserForm;
import com.example.spring1.entity.Article;
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

    public User login(UserForm dto) {
        User user = dto.toEntity(); //사용자가 입력한 정보를 갖는 객체
        User login = userRepository.findByEmailAddress(user.getEmail(), user.getPassword()); //이메일을 건내서 해당 객체 반환
        if(login==null){
            return null;
        }
        return login;
    }

    public User emailCheck(String email) {
        User check = userRepository.findByEmail(email);
        if(check != null){
            return null;
        }
        return check;
    }
}
