package com.furnity.furnity.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.furnity.furnity.model.User;
import com.furnity.furnity.service.SecurityService;
import com.furnity.furnity.service.UserService;

@Controller
public class UserController {

	
	@Autowired
	UserService userService;
	
    @Autowired
    private SecurityService securityService;

    User user = null;

	
	@GetMapping({"/", "/home"})
	public String home() {
		// model.addAttribute("customers", customerservice.listAllCustomers());			
		return "home";
	}	
	
	@GetMapping(value = "/login")
	public String login() {
		// model.addAttribute("customers", customerservice.listAllCustomers());
		System.out.println("Redirecting to products.html page.");
		return "login";
	}
	
	@PostMapping(value = "/login")
	public String login(@ModelAttribute("user") User userForm, BindingResult bindingResult, Model model, HttpSession session) {
		System.out.println("====userform=====");
		
        if (securityService.isAuthenticated()) {
        	session.setAttribute("isUserLoggedIn",true);
        	return "home";
        } else {
        	try {
        		
        		user = userService.findByEmail(userForm.getEmail());
        		
        		 if (user == null) {
        			 model.addAttribute("error", "Your username and password is invalid.");
        			 return "login";
        		 }
        		securityService.autoLogin(userForm.getEmail(), userForm.getPassword());	
        	
        
	        	if (securityService.isAuthenticated()) {
	        		session.setAttribute("isUserLoggedIn", true);
	        		user = userService.findByEmail(userForm.getEmail());
	        		System.out.println("user: " + user.toString());
	        		return "home";
	        	} else {
	        		return "login";
	        	} 
	        	
        	} catch(Exception e) {
        		e.printStackTrace();
        		return "login";
        	}
        }
		/*
		 * if(bindingResult.hasErrors()) { return "login?error"; }
		 */
	
	}
	
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		// model.addAttribute("customers", customerservice.listAllCustomers());
		System.out.println("Redirecting to products.html page.");
		session.setAttribute("isUserLoggedIn", false);
		return "home";
	}
	
	@GetMapping(value = "/register")
	public String register() {
		// model.addAttribute("customers", customerservice.listAllCustomers());
		System.out.println("Redirecting to products.html page.");
		return "register";
	}
	
	@PostMapping(value = "/register")
	public String register(@ModelAttribute("user") User user, BindingResult bindingResult) {
	//	userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

       userService.save(user);

       // securityService.autoLogin(user.getUsername(), user.getPasswordConfirm());

       // return "redirect:/welcome";
		return "home";
	}
	
	
	
	
	@GetMapping(value = "/add_furniture")
	public String addFurniture() {
		// model.addAttribute("customers", customerservice.listAllCustomers());
		System.out.println("Redirecting to products.html page.");
		return "add_furniture";
	}
}
