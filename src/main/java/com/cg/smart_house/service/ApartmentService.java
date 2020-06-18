package com.cg.smart_house.service;

import com.cg.smart_house.dto.apartment.ApartmentDtoRequest;
import com.cg.smart_house.dto.apartment.ApartmentDtoResponse;
import com.cg.smart_house.model.Apartment;

public interface ApartmentService {
    ApartmentDtoResponse createApartment(ApartmentDtoRequest request);

    ServiceResult findAll();

    ServiceResult findById(Long id);

    ServiceResult deleteApartment(Long id);

    ServiceResult updateApartment(Apartment apartment);

}
