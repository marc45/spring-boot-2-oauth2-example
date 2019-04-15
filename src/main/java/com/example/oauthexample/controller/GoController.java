package com.example.oauthexample.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class GoController {
//    @GetMapping("/go")
//    String go(Model model){
//        return "add-user";
//    }

    @GetMapping("/go")
    public String go(Model model, @RequestParam(value="name", required=false, defaultValue="World") String name) {
        model.addAttribute("name", name);
        return "login";
    }
}
