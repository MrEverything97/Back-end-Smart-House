package com.cg.smart_house.service.impl;

import com.cg.smart_house.models.Category;
import com.cg.smart_house.repository.CategoryRepository;
import com.cg.smart_house.service.CategoryService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ServiceResult createCategory(Category category) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(categoryRepository.save(category));
        return serviceResult;
    }

    @Override
    public ServiceResult findAll() {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(categoryRepository.findAll());
        return serviceResult;
    }

    @Override
    public ServiceResult findById(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            serviceResult.setMessage("Category Not Found");
        }
        serviceResult.setData(category);
        return serviceResult;
    }

    @Override
    public ServiceResult deleteCategory(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Category category = categoryRepository.findById(id).orElse(null);
        if (category == null) {
            serviceResult.setStatus(ServiceStatus.FAILED);
            serviceResult.setMessage("Category Not Found");
        } else {
            categoryRepository.delete(category);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult updateCategory(Category category) {
        ServiceResult serviceResult = new ServiceResult();
        if (categoryRepository.findById(category.getId()).isPresent()) {
            serviceResult.setMessage("Category not found");
        } else {
            serviceResult.setData(categoryRepository.save(category));
        }
        return serviceResult;
    }
}
