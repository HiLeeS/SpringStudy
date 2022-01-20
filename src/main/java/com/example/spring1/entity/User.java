package com.example.spring1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@AllArgsConstructor
@NoArgsConstructor  //디폴트 생성자 추가
@ToString
@Getter //모든 get을 사용가능
@Entity // DB가 해당 객체를 인식 가능!
public class User {

    @Id // 대표값을 지정! like a 주민등록번호
    @GeneratedValue // 1, 2, 3,...자동 생성 이노테이션!
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String birth;

    @Column
    private String major;

    @Column
    private String hobby;
}
