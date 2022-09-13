package com.furnity.furnity.controller;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.furnity.furnity.model.Category;
import com.furnity.furnity.model.Item;
import com.furnity.furnity.model.Role;
import com.furnity.furnity.model.User;
import com.furnity.furnity.service.CategoryService;
import com.furnity.furnity.service.ItemService;
import com.furnity.furnity.service.SecurityService;
import com.furnity.furnity.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private final ItemService itemService;

	@Autowired
	private SecurityService securityService;

	User user = null;

    @Autowired
    private final CategoryService categoryService;

   
	public UserController(ItemService itemService, CategoryService categoryService) {
		this.itemService = itemService;
		this.categoryService = categoryService;

	}

	@GetMapping({ "/", "/home" })
	public String home(Model model, HttpSession session, String keyword) {
		// model.addAttribute("customers", customerservice.listAllCustomers());

		//User data used in header
		if (securityService.isAuthenticated()) {
			User user = (User) session.getAttribute("isUserLoggedInData");
			model.addAttribute("user", user);
		}

		//item data 
		List<Item> itemList = itemService.findLatestItems(8);
		model.addAttribute("itemList", itemList);
		
		//category data
        List<Category> categoryList = categoryService.findAllCategories();
        model.addAttribute("categoryList" , categoryList);		

		return "home";
	}

	@GetMapping(value = "/login")
	public String login() {
		// System.out.println("Redirecting to products.html page.");
		return "login";
	}

	@PostMapping(value = "/login")
	public String login(@ModelAttribute("user") User userForm, BindingResult bindingResult, Model model,
			HttpSession session) {
		System.out.println("====userform=====");

		/*
		 * if (securityService.isAuthenticated()) {
		 * session.setAttribute("isUserLoggedIn",true); return "home"; } else {
		 */

		try {

			user = userService.findByEmail(userForm.getEmail());
			if (user == null) {
				model.addAttribute("error", "Your username and password is invalid.");
				model.addAttribute("user", null);
				return "login";
			}
			System.out.println("user valeu test:" + user.toString());

			securityService.autoLogin(userForm.getEmail(), userForm.getPassword());
			if (securityService.isAuthenticated()) {
				session.setAttribute("isUserLoggedIn", true);
           
				user = userService.findByEmail(userForm.getEmail());
				Set<Role> roles=user.getRoles();
				session.setAttribute("isAdmin", "false");
			//	boolean isadmin=false;
				roles.stream().forEach(role->{ 
					if(role.getName().equalsIgnoreCase("admin"))
					{
						//isadmin=true;
						session.setAttribute("isAdmin", "true");
					}
				});
				if (session.getAttribute("isAdmin").equals("true")) {
					
					return "admin";
				}
				session.setAttribute("isUserLoggedInData", user);
				
				model.addAttribute("user", user);
				System.out.println("user: " + user.toString());
				return "home";
			} else {
				return "login";
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "login";
		}
		// }
		/*
		 * if(bindingResult.hasErrors()) { return "login?error"; }
		 */

	}

	@GetMapping(value = "/logout")
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";
	}

	@GetMapping(value = "/register")
	public String register() {

		System.out.println("Redirecting to products.html page.");
		return "register";
	}

	@PostMapping(value = "/register")
	public String register(@ModelAttribute("user") User user, BindingResult bindingResult,Model model) {
		
		// userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return "register";
		}

		userService.save(user);
		model.addAttribute("user",null);
		//user=null;
		// securityService.autoLogin(user.getEmail(), user.getPassword());
		// return "redirect:/welcome";
		return "login";
	}

	@GetMapping(value = "/add_furniture")
	public String addFurniture() {
		// model.addAttribute("customers", customerservice.listAllCustomers());
		System.out.println("Redirecting to products.html page.");
		return "add_furniture";
	}
	

}