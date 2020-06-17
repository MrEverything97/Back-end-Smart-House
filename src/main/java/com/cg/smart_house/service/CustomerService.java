package com.cg.smart_house.service;

import com.cg.smart_house.models.Apartment;
import com.cg.smart_house.models.Customer;

public interface CustomerService {
    ServiceResult createCustomer(Customer customer);

    ServiceResult findAll();

    ServiceResult findById(Long id);

    ServiceResult deleteCustomer(Long id);

    ServiceResult updateCustomer(Customer customer);
}
