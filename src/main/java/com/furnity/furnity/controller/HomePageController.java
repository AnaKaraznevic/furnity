package com.furnity.furnity.controller;

import com.furnity.furnity.model.Item;
import com.furnity.furnity.model.User;
import com.furnity.furnity.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = {"", "/"})
public class HomePageController {

    @Autowired
    private final ItemService itemService;

    public HomePageController( ItemService itemService ) {
        this.itemService = itemService;
    }

    @GetMapping
    public String index( @AuthenticationPrincipal User user, Model model) {
        if(user != null){
            model.addAttribute("user", user.getUsername());
            return "index";
        }
        List<Item> itemList = itemService.findAllItems();
        model.addAttribute("itemList", itemList);
        model.addAttribute("user", "anonymous");
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PreAuthorize(value = "hasAuthority('USER') or hasAuthority('ADMIN')")
    @GetMapping("/user")
    public String user(){
        return "user";
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @GetMapping("/admin")
        public String admin(){
            return "admin";
        }

    }
