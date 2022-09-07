package com.furnity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.furnity.entities.User;
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
		System.out.println("Redirecting to products.html pageeeee.");
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

	@PostMapping(value = "/home")
	public String home(User user, Model model) {
		// System.out.println(user);
		// System.out.println(user.getEmail());
		model.addAttribute("user", userservice.findByEmailPass(user));
		User user1 = userservice.findByEmailPass(user);
		// System.out.println("cus.getFname(): " + user);
		if (user1 != null) {
			System.out.println("not null: customer ");
			return "home";
		} else {
			System.out.println(" Null:: customer is null ");
			return "login";
		}
	}

	/*
	 * @PostMapping(value = "/register") public String register(User user, Model
	 * model) { System.out.println(user); System.out.println(user.getEmail());
	 * model.addAttribute("user", userservice.saveUser(user)); User user1 =
	 * userservice.saveUser(user); return "login";
	 * 
	 * }
	 */

	@PostMapping(value = "/register")
	public String register(User user, @RequestParam("filename") MultipartFile multipartFile) {

		/*
		 * System.out.println(multipartFile.getOriginalFilename());
		 * System.out.println(multipartFile.getResource());
		 * System.out.println(multipartFile.getContentType());
		 * System.out.println(user.getFilename());
		 */
		User user1 = userservice.saveUser(user, multipartFile);
		if (multipartFile.isEmpty()) {
			return "home";
		}
		
		return "login";

	}

}
