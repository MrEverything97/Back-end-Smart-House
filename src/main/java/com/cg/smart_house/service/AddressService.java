package com.cg.smart_house.service;

import com.cg.smart_house.dto.apartment.ApartmentDtoRequest;
import com.cg.smart_house.dto.apartment.ApartmentDtoResponse;
import com.cg.smart_house.model.Address;
import com.cg.smart_house.model.Apartment;

public interface AddressService {
    ServiceResult createAddress(Address address);

    ServiceResult findAll();

    ServiceResult findById(Long id);

    ServiceResult deleteAddress(Long id);

    ServiceResult updateAddress(Address address);
}
