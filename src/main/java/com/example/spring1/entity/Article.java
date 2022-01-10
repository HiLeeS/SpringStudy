package com.example.spring1.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor  //디폴트 생성자 추가
@ToString
@Getter //모든 get을 사용가능
@Entity // DB가 해당 객체를 인식 가능!
public class Article {

    @Id // 대표값을 지정! like a 주민등록번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) //DB가 id를 자동으로 생성하는 어노테이션
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;

    }
}


