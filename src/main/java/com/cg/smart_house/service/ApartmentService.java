package com.cg.smart_house.service;

import com.cg.smart_house.model.Apartment;
<<<<<<< HEAD
import com.cg.smart_house.model.Picture;

import java.util.List;
=======
>>>>>>> 5c756754e611cad23b6af99de1cf0587f25e6bc4

public interface ApartmentService {
    ServiceResult createApartment(Apartment apartment);

    ServiceResult updateApartment(Long id,Apartment apartment);

    ServiceResult findAll();

    ServiceResult findById(Long id);

    ServiceResult deleteApartment(Long id);

    ServiceResult findTopByPriceByDate(int price);

    //    Apartment saveApartment(Apartment apartment);

    //    List<Picture> savePictures(Apartment apartmentObj, Apartment apartment);
}
