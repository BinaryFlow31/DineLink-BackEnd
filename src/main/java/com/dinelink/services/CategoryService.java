package com.dinelink.services;

import com.dinelink.entities.AllCategoryResponse;
import com.dinelink.entities.Category;
import com.dinelink.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<AllCategoryResponse> fetchAllCategories() {
        List<Category> list=  categoryRepository.findAll();
        List<AllCategoryResponse> catList = new ArrayList<>();
        for(Category category : list) {
            AllCategoryResponse response = new AllCategoryResponse();
            response.setName(category.getName());
            response.setCategoryId(category.getCategoryId());
            response.setPhoto(category.getPhoto());

            catList.add(response);
        }

        return catList;
    }

    public Category findById(Integer categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }
}
