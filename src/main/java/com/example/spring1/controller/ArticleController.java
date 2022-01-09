package com.example.spring1.controller;

import com.example.spring1.dto.ArticleForm;
import com.example.spring1.entity.Article;
import com.example.spring1.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j  //로깅을 위한 골벵이(어노테이션)
public class ArticleController {

    @Autowired  //스프링 부트가 미리 생서해놓은 객체를 가져다가 자동으로 연결해줌
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String articleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        //System.out.println(form.toString()); -> 로깅 기능으로 대체
        log.info(form.toString());

        //1. Dto로 변환, Entity
        Article article = form.toEntity();
        //System.out.println(article.toString()); -> 로깅 기능으로 대체
        log.info(article.toString());

        //2. Repository에게 Entity를 DB에 저장하게 함.
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString()); -> 로깅 기능으로 대체
        log.info(saved.toString());

        return "redirect:/articles/"+saved.getId();
    }
    
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("Article_id = "+id);
        //1. id로 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //2. 가져온 데이터를 모델에 등록하기
        model.addAttribute("article", articleEntity);

        //3. 보여줄 페이지를 설정하기
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model) {
        //1. 모든 Article 가져오기
        List<Article> articleEntityList = articleRepository.findAll();

        //2. 가져온 Article 묶음을 뷰로 전달하기
        model.addAttribute("articleList",articleEntityList);

        //3. 뷰 페이지를 설정하기
        return "articles/index";    //articles/index.mustache
    }

    @GetMapping("/articles/{id}/edit")  //url에서 받아올 값의 변수명과 {id}
    public String edit(@PathVariable Long id, Model model){ //매개변수로 받을 변수명은 같아야 함. @PathVariable일때
        //수정할 데이터를 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //모델에 데이터 등록하기
        model.addAttribute("article", articleEntity);

        // 뷰 페이지 설정하기
        return "articles/edit";
    }
}
