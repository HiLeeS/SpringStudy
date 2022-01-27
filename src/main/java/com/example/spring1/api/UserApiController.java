package com.example.spring1.api;

import com.example.spring1.dto.ArticleForm;
import com.example.spring1.dto.UserForm;
import com.example.spring1.entity.Article;
import com.example.spring1.entity.User;
import com.example.spring1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController //Rest API 용 컨트롤러, 데이터(JSON)를 반환
public class UserApiController {
    @Autowired  //DI
    private UserService userService;

    //POST
    @PostMapping("/api/users/check-email")
    public boolean emailCheck(@PathVariable String email){
        log.info(email);
        User check = userService.emailCheck(email);
        return (check != null) ? true:false;
    }

    //POST
    @PostMapping("/api/users/create")
    public ResponseEntity<User> create(@RequestBody UserForm dto){
        log.info(dto.toString());
        User created = userService.create(dto);
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //POST, 로그인하기
    @PostMapping("/api/login")
    public ResponseEntity<User> login(@RequestBody UserForm dto){
        log.info(dto.toString());
        User login = userService.login(dto);
        return (login != null) ?
                ResponseEntity.status(HttpStatus.OK).body(login):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //PATCH
    @PatchMapping("/api/users/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserForm dto){
        User updated = userService.update(id,dto);
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //DELETE
    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        User deleted = userService.delete(id);
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.OK).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //트랜잭션 -> 실패 -> 롤백
    @PostMapping("/api/transaction-test")
    public ResponseEntity<List<User>> transactionTest(@RequestBody List<UserForm> dtos){
        List<User> createdList = userService.createArticles(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}
