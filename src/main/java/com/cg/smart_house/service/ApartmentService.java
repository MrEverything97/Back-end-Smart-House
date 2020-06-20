package com.cg.smart_house.service;

import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Picture;

import java.util.List;

public interface ApartmentService {
    ServiceResult createApartment(Apartment apartment);

    ServiceResult updateApartment(Long id,Apartment apartment);

    ServiceResult findAll();

    ServiceResult findById(Long id);

    ServiceResult deleteApartment(Long id);

    ServiceResult findTopByPriceByDate(int price);

    ServiceResult findTop5ByPriceByDateAndNameContains(int price, String name);

    //    Apartment saveApartment(Apartment apartment);

    //    List<Picture> savePictures(Apartment apartmentObj, Apartment apartment);
}
