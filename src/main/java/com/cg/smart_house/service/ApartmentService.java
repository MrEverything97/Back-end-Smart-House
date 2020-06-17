package com.cg.smart_house.service;

import com.cg.smart_house.models.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface ApartmentService {
    ServiceResult createApartment(Apartment apartment);
}
