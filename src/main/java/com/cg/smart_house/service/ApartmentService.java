package com.cg.smart_house.service;

import com.cg.smart_house.enumm.StatusOrders;
import com.cg.smart_house.model.Apartment;

import com.cg.smart_house.model.Picture;
import com.cg.smart_house.model.User;

import java.util.Date;
import java.util.List;


public interface ApartmentService {
    ServiceResult createApartment(Apartment apartment);
    ServiceResult updateApartment(Long id, Apartment apartment);
    ServiceResult updateApartmentPicture(Long id, List<Picture> pictureList);
    ServiceResult findAll();
    ServiceResult findById(Long id);
    ServiceResult searchApartment(int bedroom, int bathroom, Long province_id, int startPrice, int endPrice, Date startTime, Date endTime);
    ServiceResult searchApartmentByStatus(User user, StatusOrders statusOrders);
}

