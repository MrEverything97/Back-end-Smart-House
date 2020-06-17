package com.cg.smart_house.service.impl;

import com.cg.smart_house.models.Customer;
import com.cg.smart_house.models.Picture;
import com.cg.smart_house.repository.CustomerRepository;
import com.cg.smart_house.service.CustomerService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ServiceResult createCustomer(Customer customer) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(customerRepository.save(customer));
        return serviceResult;
    }

    @Override
    public ServiceResult findAll() {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(customerRepository.findAll());
        return serviceResult;
    }

    @Override
    public ServiceResult findById(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            serviceResult.setMessage("Customer Not Found");
        }
        serviceResult.setData(customer);
        return serviceResult;
    }

    @Override
    public ServiceResult deleteCustomer(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Customer customer = customerRepository.findById(id).orElse(null);
        if (customer == null) {
            serviceResult.setStatus(ServiceStatus.FAILED);
            serviceResult.setMessage("Customer Not Found");
        } else {
            customerRepository.delete(customer);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult updateCustomer(Customer customer) {
        ServiceResult serviceResult = new ServiceResult();
        if (customerRepository.findById(customer.getId()).isPresent()) {
            serviceResult.setMessage("Customer not found");
        } else {
            serviceResult.setData(customerRepository.save(customer));
        }
        return serviceResult;
    }
}
