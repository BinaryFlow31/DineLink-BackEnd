package com.dinelink.controllers;

import com.dinelink.entities.*;
import com.dinelink.exceptions.CategoryNotFound;
import com.dinelink.exceptions.SubCategoryNotFound;
import com.dinelink.services.CategoryService;
import com.dinelink.services.FoodItemService;
import com.dinelink.services.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:5173")
public class MenuController {
    private CategoryService categoryService;
    private SubCategoryService subCategoryService;
    private FoodItemService foodItemService;

    @Autowired
    public MenuController(CategoryService categoryService, SubCategoryService subCategoryService, FoodItemService foodItemService) {
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
        this.foodItemService = foodItemService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<AllCategoryResponse>> getAllCategories() {
        List<AllCategoryResponse> responses =  categoryService.fetchAllCategories();

        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PostMapping("/menu/{newSub}")
    public ResponseEntity<MenuCreationResponse> createMenu(@PathVariable("newSub") Boolean newSub, @RequestBody MenuCreationRequest menuCreationRequest) {
        MenuCreationResponse response = new MenuCreationResponse();

        if(newSub) {
            Category newCategory = categoryService.findById(menuCreationRequest.getCategoryId());

            if(newCategory != null) {
                SubCategory subCategory = new SubCategory();
                subCategory.setName(menuCreationRequest.getSubCategoryName());
                subCategory.setCategory(newCategory);

                SubCategory newSubCategory = subCategoryService.saveSubCategory(subCategory);

                FoodItem foodItem = new FoodItem();
                foodItem.setSubCategory(newSubCategory);
                foodItem.setName(menuCreationRequest.getItemName());
                foodItem.setPrice(menuCreationRequest.getPrice());

                FoodItem newFoodItem = foodItemService.saveFoodItem(foodItem);

                response.setCategory(newCategory);
                response.setSubCategory(newSubCategory);
                response.setFoodItem(newFoodItem);

            } else {
                throw new CategoryNotFound("Category not found in DB.");
            }
        } else {
            Category newCategory = categoryService.findById(menuCreationRequest.getCategoryId());

            if(newCategory != null) {
                SubCategory newSubCategory = subCategoryService.findById(menuCreationRequest.getSubCategoryId());

                if(newSubCategory != null) {
                    FoodItem foodItem = new FoodItem();
                    foodItem.setSubCategory(newSubCategory);
                    foodItem.setName(menuCreationRequest.getItemName());
                    foodItem.setPrice(menuCreationRequest.getPrice());

                    FoodItem newFoodItem = foodItemService.saveFoodItem(foodItem);

                    response.setCategory(newCategory);
                    response.setSubCategory(newSubCategory);
                    response.setFoodItem(newFoodItem);

                } else {
                    throw new SubCategoryNotFound("SubCategory not found in DB.");
                }
            } else {
                throw new CategoryNotFound("Category not found in DB.");
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
