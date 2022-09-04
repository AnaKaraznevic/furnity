package com.furnity.furnity.controller;

import com.furnity.furnity.model.Category;
import com.furnity.furnity.model.Item;
import com.furnity.furnity.service.CategoryService;
import com.furnity.furnity.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private final ItemService itemService;

    @Autowired
    private final CategoryService categoryService;

    public ItemController( ItemService itemService, CategoryService categoryService ) {
        this.itemService = itemService;
        this.categoryService = categoryService;
    }

    @GetMapping("/item")
    public String itemsList(Model model){
        List<Item> itemList = itemService.findAllItems();
        model.addAttribute("itemList" , itemList);
        return "item_list";
    }

    @GetMapping("/item/new")
    public String showNewItemForm( Model model){
        List<Category> categoryList = categoryService.findAllCategories();
        model.addAttribute("item", new Item());
        model.addAttribute("categoryList", categoryList);
        return "new_item_form";
    }

    @PostMapping("/item/save")
    public String saveNewItem(Item item){
        itemService.addItem(item);
        return "redirect:/item";
    }
}
