package com.furnity.furnity.controller;

import com.furnity.furnity.model.Category;
import com.furnity.furnity.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
//@RequestMapping("/api/v1/")
public class CategoryController {

    @Autowired
    private final CategoryService categoryService;

    public CategoryController( CategoryService categoryService ) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category/")
    public String categoriesList(Model model){
        List<Category> categoryList = categoryService.findAllCategories();
        model.addAttribute("categoryList" , categoryList);
        return "category_list";
    }

    @GetMapping("/category/new")
    public String showNewCategoryForm(Model model){
        model.addAttribute("category", new Category());
        return "new_category_form";
    }

    @PostMapping("/category/save")
    public String saveNewCategory(Category category){
        categoryService.addCategory(category);
        return "redirect:/category";
    }
}
