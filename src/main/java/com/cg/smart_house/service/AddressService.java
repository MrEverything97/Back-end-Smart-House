package com.cg.smart_house.service;

import com.cg.smart_house.model.Address;

public interface AddressService {
    ServiceResult createAddress(Address address);

    ServiceResult findAll();

    ServiceResult findById(Long id);

    ServiceResult deleteAddress(Long id);

    ServiceResult updateAddress(Address address);
}