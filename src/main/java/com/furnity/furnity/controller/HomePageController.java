package com.furnity.furnity.controller;

import com.furnity.furnity.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePageController {

    @GetMapping
    public String index( @AuthenticationPrincipal User user, Model model) {
        if(user != null){
            model.addAttribute("user", user.getUsername());  // ??????? or email ?
            return "index2";
        }

        model.addAttribute("user", "anonymous");
        return "index2";
    }

    @GetMapping("/login")
    public String login(){
        return "login2";
    }

    @PreAuthorize(value = "hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/user")
    public String user(){
        return "user2";
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping("/admin")
        public String admin(){
            return "admin2";
        }

    }