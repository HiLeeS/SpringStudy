package com.example.spring1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String toString(Model model) {
        model.addAttribute("username", "승준");
        return"greetings";  // src/resources/templates/greetings.mustache -> 브라우저로 전송!
    }

    @GetMapping("/bye")
    public String bye(Model model){
        model.addAttribute("nickname","승준");
        return "goodbye";
    }
}
