package com.furnity.furnity.controller;

import com.furnity.furnity.model.Item;
import com.furnity.furnity.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    @Autowired
    private final ItemService itemService;

    public ItemController( ItemService itemService ) {
        this.itemService = itemService;
    }

    @GetMapping("/item/new")
    public String showNewItemForm( Model model){
        model.addAttribute("item", new Item());
        return "new_item_form";
    }
}
