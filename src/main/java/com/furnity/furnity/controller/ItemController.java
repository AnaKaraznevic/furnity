package com.furnity.furnity.controller;

import com.furnity.furnity.model.Category;
import com.furnity.furnity.model.Item;
import com.furnity.furnity.model.User;
import com.furnity.furnity.service.CategoryService;
import com.furnity.furnity.service.ItemService;
import com.furnity.furnity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ItemController {

	@Autowired
	private final ItemService itemService;

	@Autowired
	private final CategoryService categoryService;

	@Autowired
	private final UserService userService;

	public ItemController(ItemService itemService, CategoryService categoryService, UserService userService) {
		this.itemService = itemService;
		this.categoryService = categoryService;
		this.userService = userService;
	}

	@GetMapping("/item/new")
	public String showNewItemForm(Model model) {
		List<Category> categoryList = categoryService.findAllCategories();
		model.addAttribute("item", new Item());
		model.addAttribute("categoryList", categoryList);
		return "item_form";
	}

	@PostMapping("/item/save")
	public String saveNewItem(Item item, @RequestParam("filename") MultipartFile multipartFile) {

		User user = getLoggedUser();
		item.setUser(user);

		if (multipartFile.isEmpty()) {
			System.out.println("empty filename");
			return "redirect:/item/new";
		}

		if (!((multipartFile.getContentType().equals("image/jpeg"))
				|| (multipartFile.getContentType().equals("image/png")))) {
			System.out.println("Only jpeg and PNG file allowed");
			return "redirect:/item/new";
		}

		Item item1 = itemService.addItem(item, multipartFile);
		if (item1 != null) {
			System.out.println("inserted");
			return "redirect:/item/user";
		} else {
			System.out.println("not inserted");
			return "redirect:/item/new";
		}

	}

	@GetMapping("/item/delete/{id}")
	public String deletePro(@PathVariable("id") Long id) {
		this.itemService.deleteItem(id);
		return "redirect:/item";
	}

	@GetMapping("/item/edit/{id}")
	public String editItem(@PathVariable(value = "id") Long id, Model model) {
		List<Category> categoryList = categoryService.findAllCategories();
		model.addAttribute("categoryList", categoryList);
		Item item = itemService.findItemById(id);
		model.addAttribute("item", item);
		System.out.println(item);
		return "item_form";
	}

	//@RequestMapping(path = { "/item" })
	@GetMapping("/item")
	public String search(Model model, String keyword) {

		if (keyword != null) {
			List<Item> itemList = itemService.findItemsByKeyword(keyword);
			model.addAttribute("itemList", itemList);
		} else {
			List<Item> itemList = itemService.findAllItems();
			model.addAttribute("itemList", itemList);

		}
		return "item_list";
	}

	@GetMapping("/item/user")
	public String searchUser(Model model, String keyword) {

		User user = getLoggedUser();

		if (keyword != null) {
			List<Item> itemList = itemService.findItemsByKeywordAndUserId(keyword, user.getId());
			model.addAttribute("itemList", itemList);
		} else {
			List<Item> itemList = itemService.findItemsByUserId(user.getId());
			model.addAttribute("itemList", itemList);

		}
		return "item_list_user";
	}

	public User getLoggedUser ()
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String userName;

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		User user = userService.findUserByUserName(userName);
		return user;
	}


}
