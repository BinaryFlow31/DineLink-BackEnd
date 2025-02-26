package com.dinelink.services;

import com.dinelink.entities.Category;
import com.dinelink.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    private CategoryRepository categoryRepository;

    @Autowired
    public MenuService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> fetchAllCategories() {
        return categoryRepository.findAll();
    }
}
