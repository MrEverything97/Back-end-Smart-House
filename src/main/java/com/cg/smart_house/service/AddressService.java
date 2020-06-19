package com.cg.smart_house.service;

import com.cg.smart_house.model.Address;

public interface AddressService {
    ServiceResult createAddress(Address address);
    ServiceResult findAll();
}
