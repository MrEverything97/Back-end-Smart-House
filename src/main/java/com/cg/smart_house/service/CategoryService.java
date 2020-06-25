package com.cg.smart_house.service;

import com.cg.smart_house.model.Category;

public interface CategoryService {
    ServiceResult createCategory(Category category);

    ServiceResult findAll();

    ServiceResult findById(Long id);

    ServiceResult deleteCategory(Long id);

    ServiceResult updateCategory(Category category);
}
