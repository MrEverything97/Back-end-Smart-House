package com.cg.smart_house.service.impl;


import com.cg.smart_house.model.*;
import com.cg.smart_house.repository.*;
import com.cg.smart_house.service.ApartmentService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    ProvinceRepository provinceRepository;

//    private Picture savePicture();

    @Override
    public ServiceResult createApartment(Apartment apartment) {
        ServiceResult serviceResult = new ServiceResult();

        Apartment apartmentObj = this.saveAppartment(apartment);
        this.savePictures(apartmentObj, apartment);

        return serviceResult;
    }

    @Override
    public ServiceResult findAll() {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(apartmentRepository.findAll());
        return serviceResult;
    }

    @Override
    public ServiceResult findById(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Apartment apartment = apartmentRepository.findById(id).orElse(null);
        if (apartment == null) {
            serviceResult.setMessage("Apartment Not Found");
        }
        serviceResult.setData(apartment);
        return serviceResult;
    }

    @Override
    public ServiceResult deleteApartment(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Apartment apartment = apartmentRepository.findById(id).orElse(null);
        if (apartment == null) {
            serviceResult.setStatus(ServiceStatus.FAILED);
            serviceResult.setMessage("Apartment Not Found");
        }
        else {
            apartmentRepository.delete(apartment);
        }
        return serviceResult;
    }

    @Override
    public Apartment saveAppartment(Apartment apartment) {
        return null;
    }

    @Override
    public List<Picture> savePictures(Apartment apartmentObj, Apartment apartment) {
        return null;
    }

    @Override
    public ServiceResult updateApartment(Apartment apartment) {
        ServiceResult serviceResult = new ServiceResult();
        if (!apartmentRepository.findById(apartment.getId()).isPresent()){
            serviceResult.setMessage("Apartment not found");
        } else{
            serviceResult.setData(apartmentRepository.save(apartment));
        }
        return serviceResult;
    }
}
