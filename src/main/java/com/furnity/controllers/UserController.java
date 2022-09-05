package com.furnity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.furnity.services.UserService;

@Controller
public class UserController {

	
	@Autowired
	UserService userservice;
	
	@GetMapping(value = "/")
	public String home() {
		// model.addAttribute("customers", customerservice.listAllCustomers());
		System.out.println("Redirecting to products.html page.");
		return "home";
	}	
	
	@GetMapping(value = "/login")
	public String login() {
		// model.addAttribute("customers", customerservice.listAllCustomers());
		System.out.println("Redirecting to products.html page.");
		return "login";
	}
	
	@GetMapping(value = "/register")
	public String register() {
		// model.addAttribute("customers", customerservice.listAllCustomers());
		System.out.println("Redirecting to products.html page.");
		return "register";
	}
	@GetMapping(value = "/add_furniture")
	public String addFurniture() {
		// model.addAttribute("customers", customerservice.listAllCustomers());
		System.out.println("Redirecting to products.html page.");
		return "add_furniture";
	}
}
