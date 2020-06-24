package com.cg.smart_house.service;

import com.cg.smart_house.model.Apartment;

import com.cg.smart_house.model.Picture;

import java.util.Date;
import java.util.List;


public interface ApartmentService {
    ServiceResult createApartment(Apartment apartment, String username);
    ServiceResult updateApartment(Long id, Apartment apartment);
    ServiceResult updateApartmentPicture(Long id, List<Picture> pictureList);
    ServiceResult findAll();
    ServiceResult findById(Long id);
    ServiceResult searchApartment(int bedroom, int bathroom, Long province_id, int startPrice, int endPrice, Date startTime, Date endTime);
}

