package com.furnity.furnity.web;

import com.furnity.furnity.service.UserService;
import com.furnity.furnity.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private final UserService userService;

    public UserRegistrationController( UserService userService ) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }

    @PostMapping
    public String registerNewUserAccount( @ModelAttribute("user")UserRegistrationDto registrationDto ) {
        userService.saveNewUser(registrationDto);
        return "redirect:/registration?success";
    }

}
