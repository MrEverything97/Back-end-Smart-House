package com.cg.smart_house.service;

import com.cg.smart_house.model.Apartment;

public interface ApartmentService {
    ServiceResult createApartment(Apartment apartment);
    ServiceResult updateApartment(Apartment apartment);
    ServiceResult findAll();
    ServiceResult findById(Long id);
    ServiceResult deleteApartment(Long id);

}
