package com.cg.smart_house.service;

import com.cg.smart_house.model.Apartment;

import com.cg.smart_house.model.Picture;

import java.util.Date;
import java.util.List;


public interface ApartmentService {
    ServiceResult createApartment(Apartment apartment);

    ServiceResult updateApartment(Long id,Apartment apartment);
    ServiceResult updateApartmentPicture(Long id,List<Picture> pictureList);

    //Tim Kiem
//    ServiceResult searchAllByApartment(int bedroom, int bathroom, int min, int max, String address, Date startTime, Date endTime);

    ServiceResult findAll();

    ServiceResult findById(Long id);

    ServiceResult deleteApartment(Long id);
    ServiceResult findAllByHostId(Long hostId);
    ServiceResult searchApartment(int bedroom, int bathroom, Long province_id, int startPrice, int endPrice, Date startTime, Date endTime);

    ServiceResult findTopByPriceByDate(int price);

    ServiceResult findTop5ByPriceByDateAndNameContains(int price, String name);

    ServiceResult findAllByPriceByDate(int minPrice, int maxPrice);

//    ServiceResult findAllOrderByStartTimeAndEndTime(Date startTime, Date endTime);

    //    Apartment saveApartment(Apartment apartment);

    //    List<Picture> savePictures(Apartment apartmentObj, Apartment apartment);
}
