package com.furnity.furnity.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.furnity.furnity.model.Category;
import com.furnity.furnity.model.Item;
import com.furnity.furnity.model.User;
import com.furnity.furnity.service.CategoryService;
import com.furnity.furnity.service.ItemService;
import com.furnity.furnity.service.SecurityService;

@Controller
public class ItemController {

	@Autowired
	private final ItemService itemService;

	@Autowired
	private final CategoryService categoryService;
	@Autowired
	private SecurityService securityService;

	public ItemController(ItemService itemService, CategoryService categoryService) {
		this.itemService = itemService;
		this.categoryService = categoryService;
	}

	@GetMapping("/item/new")
	public String showNewItemForm(Model model, HttpSession session) {
		if (securityService.isAuthenticated()) {
			User user = (User) session.getAttribute("isUserLoggedInData");
			model.addAttribute("user", user);
		}
		List<Category> categoryList = categoryService.findAllCategories();
		model.addAttribute("item", new Item());
		model.addAttribute("categoryList", categoryList);
		return "item_form";
	}

	@PostMapping("/item/save")
	public String saveNewItem(Item item, @RequestParam("filename") MultipartFile multipartFile, HttpSession session,
			Model model) {
		/*
		 * if (multipartFile.isEmpty()) { System.out.println("empty filename"); }
		 */

		
		if (!((multipartFile.getContentType().equals("image/jpeg"))
				|| (multipartFile.getContentType().equals("image/png"))
				|| multipartFile.getContentType().equals("application/octet-stream"))) {
			System.out.println("Only jpeg and PNG file allowed");
			System.out.println(item.getId());
			if (item.getId() != 0) {
				return "redirect:/item/edit/" + item.getId();
			} else {
				return "redirect:/item/new";
			}
		}

		Item item1 = itemService.addItem(item, multipartFile);
		if (item1 != null) {
			System.out.println("inserted");
			return "redirect:/item";
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
	public String editItem(@PathVariable(value = "id") Long id, Model model, HttpSession session) {
		if (securityService.isAuthenticated()) {
			User user = (User) session.getAttribute("isUserLoggedInData");
			model.addAttribute("user", user);
		}
		List<Category> categoryList = categoryService.findAllCategories();
		model.addAttribute("categoryList", categoryList);
		Item item = itemService.findItemById(id);
		model.addAttribute("item", item);
		System.out.println(item);
		return "item_form";
	}

	@RequestMapping(path = { "/item" })
	public String search(Model model, String keyword, HttpSession session) {
		if (securityService.isAuthenticated()) {
			User user = (User) session.getAttribute("isUserLoggedInData");
			model.addAttribute("user", user);
		}
		if (keyword != null) {
			List<Item> itemList = itemService.findItemsByKeyword(keyword);
			model.addAttribute("itemList", itemList);
		} else {
			List<Item> itemList = itemService.findAllItems();
			model.addAttribute("itemList", itemList);

		}
		return "item_list";
	}
}
