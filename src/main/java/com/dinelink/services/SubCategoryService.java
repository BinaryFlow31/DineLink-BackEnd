package com.dinelink.services;

import com.dinelink.entities.SubCategory;
import com.dinelink.repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubCategoryService {
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    public SubCategoryService(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    public SubCategory saveSubCategory(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    public SubCategory findById(Integer subCategoryId) {
        return subCategoryRepository.findById(subCategoryId).orElse(null);
    }
}
