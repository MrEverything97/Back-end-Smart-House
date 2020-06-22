package com.cg.smart_house.service;

import com.cg.smart_house.model.Apartment;

import com.cg.smart_house.model.Picture;

import java.util.Date;
import java.util.List;
import java.util.Date;


public interface ApartmentService {
    ServiceResult createApartment(Apartment apartment);

    ServiceResult updateApartment(Long id,Apartment apartment);

    ServiceResult searchAllByApartment(String name, int bedroom, int bathroom, int price, String address, Date startTime, Date endTime);

//    ServiceResult updateApartment(Apartment apartment);
    ServiceResult findAll();

    ServiceResult findById(Long id);

    ServiceResult deleteApartment(Long id);
    ServiceResult findAllByAddressAndOrderStartTimeAndEndTime(Long idProvince, Date minTime, Date maxTime);
    ServiceResult findAllCriteria(int bedroom, int bathroom, int priceByDate, Long idProvince, Date minTime, Date maxTime);
//
//    ServiceResult saveApartment(Apartment apartment);
//
//    List<Picture> savePictures(Apartment apartment);

    ServiceResult findTopByPriceByDate(int price);

    ServiceResult findTop5ByPriceByDateAndNameContains(int price, String name);

    ServiceResult findAllByPriceByDate(int minPrice, int maxPrice);

    ServiceResult findAllOrderByStartTimeAndEndTime(Date startTime, Date endTime);

    List<Picture> savePictures(Apartment apartmentObj, Apartment apartment);
}
