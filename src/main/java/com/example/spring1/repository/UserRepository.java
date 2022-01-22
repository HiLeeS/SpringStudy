package com.example.spring1.repository;

import com.example.spring1.entity.Article;
import com.example.spring1.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;

public interface UserRepository extends CrudRepository<User, Long> {
    @Override
    ArrayList<User> findAll();

    //email 전체 조회
    @Query("select u from User u where u.email = :email")
    User findByEmail(@Param("email") String email);

    //로그인 email과 password 조회
    @Query("select u from User u where u.email = :email and u.password= :password")
    User findByEmailAddress(@Param("email") String email,
                            @Param("password") String password);

}
