package com.example.spring1.controller;

import com.example.spring1.dto.UserForm;
import com.example.spring1.entity.User;
import com.example.spring1.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j  //로깅을 위한 골벵이(어노테이션)
public class UserController {

    @Autowired  //스프링 부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결해줌
    private UserRepository userRepository;

    @GetMapping("/users/new")
    public String userForm() { return "users/newUser"; }

    @PostMapping("/users/create")
    public String createUser(UserForm form) {
        //System.out.println(form.toString()); -> 로깅 기능으로 대체
        log.info(form.toString());

        //1. Dto로 변환, Entity
        User user = form.toEntity();
        //System.out.println(article.toString()); -> 로깅 기능으로 대체
        log.info(user.toString());

        //2. Repository에게 Entity를 DB에 저장하게 함.
        User saved = userRepository.save(user);
        //System.out.println(saved.toString()); -> 로깅 기능으로 대체
        log.info(saved.toString());

        return "";
    }

}
