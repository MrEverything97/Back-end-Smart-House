package com.cg.smart_house.service;

import com.cg.smart_house.models.Address;

public interface AddressService {
    ServiceResult createAddress(Address address);
    ServiceResult findAll();
}
