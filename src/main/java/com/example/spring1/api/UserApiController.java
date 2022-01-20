package com.example.spring1.api;

import com.example.spring1.dto.UserForm;
import com.example.spring1.entity.User;
import com.example.spring1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController //Rest API 용 컨트롤러, 데이터(JSON)를 반환
public class UserApiController {
    @Autowired  //DI
    private UserService userService;
    
    //POST
    @PostMapping("/users/createe")
    public ResponseEntity<User> create(@RequestBody UserForm dto){
        log.info(dto.toString());
        User created = userService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
