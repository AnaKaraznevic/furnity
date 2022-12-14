package com.furnity.furnity.service;

import com.furnity.furnity.model.Category;
import com.furnity.furnity.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService( CategoryRepository categoryRepository ) {
        this.categoryRepository = categoryRepository;
    }

    public Category addCategory( Category category ) {
        return categoryRepository.save(category);
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }
}
