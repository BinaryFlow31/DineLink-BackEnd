package com.dinelink.services;

import com.dinelink.entities.Category;
import com.dinelink.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> fetchAllCategories() {
        List<Category> list = categoryRepository.findAll();
        return list;
    }

    public Category findById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }
}
