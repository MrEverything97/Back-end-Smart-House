package com.cg.smart_house.service;

import com.cg.smart_house.models.Apartment;

public interface ApartmentService {
    ServiceResult createApartment(Apartment apartment);

    ServiceResult findAll();
}
