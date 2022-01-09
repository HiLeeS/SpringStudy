package com.example.spring1.repository;

import com.example.spring1.entity.Article;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<Article, Long> {  // <관리대상 Entity, 관리대상의 대표값 데이터 타입>

    @Override
    ArrayList<Article> findAll();
}
