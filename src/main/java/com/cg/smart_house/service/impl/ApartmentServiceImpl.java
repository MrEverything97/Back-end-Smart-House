package com.cg.smart_house.service.impl;


import com.cg.smart_house.model.*;
import com.cg.smart_house.repository.*;
import com.cg.smart_house.service.ApartmentService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


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

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    OrdersRepository ordersRepository;
//    @Override
//    public ServiceResult saveApartment(Apartment apartment) {
//        ServiceResult serviceResult = new ServiceResult();
//        serviceResult.setData(apartmentRepository.save(apartment));
//        return serviceResult;
//    }
//
//    @Override
//    public List<Picture> savePictures(Apartment apartment) {
//        List<Picture> pictures = apartment.getPictures();
//        for (Picture picture : pictures) {
//            Picture findPicture = pictureRepository.findByImageUrl(picture.getImageUrl());
//            if (findPicture == null) {
//                Picture newPicture = pictureRepository.save(picture);
//                picture.setId(newPicture.getId());
//                picture.setApartment(apartment);
//            } else {
//                picture.setId(findPicture.getId());
//            }
//        }
//        return pictures;
//    }

    @Override
    public ServiceResult createApartment(Apartment apartment) {
        ServiceResult serviceResult = new ServiceResult();

//        ServiceResult apartmentObj = this.saveApartment(apartment);
//        this.savePictures(apartmentObj);
//        return serviceResult;

        Set<Category> categories = apartment.getCategories();
        for (Category category : categories) {
            Category findCategory = categoryRepository.findByName(category.getName());
            if (findCategory == null) {
                Category newCategory = categoryRepository.save(category);
                category.setId(newCategory.getId());
            } else {
                category.setId(findCategory.getId());
            }
        }
        Address address = apartment.getAddress();
//        address.setProvinces(address.getProvinces();
        apartment.setAddress(null);

        serviceResult.setData(apartmentRepository.save(apartment));

        address.setApartment(apartment);
        addressRepository.save(address);

        List<Picture> pictures = apartment.getPictures();
        for (Picture picture : pictures) {
            Picture findPicture = pictureRepository.findByImageUrl(picture.getImageUrl());
            if (findPicture == null) {
                Picture newPicture = pictureRepository.save(picture);
                picture.setId(newPicture.getId());
                picture.setApartment(apartment);
            } else {
                picture.setId(findPicture.getId());
            }
        }
        serviceResult.setData(apartmentRepository.save(apartment));
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
        } else {
            apartmentRepository.delete(apartment);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult findAllByAddressAndOrderStartTimeAndEndTime(Long idProvince, Date minTime, Date maxTime) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setStatus(ServiceStatus.FAILED);

        Province province = provinceRepository.findById(idProvince).orElse(null);
        if (province == null) {
            serviceResult.setMessage("No find apartment by province ");
            return serviceResult;
        } else {
            List<Apartment> apartmentListByProvince = apartmentRepository.findAllByAddressProvinces(province);
            List<Order> orderList = ordersRepository.getAllByStartTimeAndEndTimeNoParam(minTime, maxTime);
            if (orderList.isEmpty()) {
                serviceResult.setStatus(ServiceStatus.SUCCESS);
                serviceResult.setData(apartmentListByProvince);
            } else {
                for (Order order : orderList) {
                    apartmentListByProvince.remove(order.getApartment());
                }
                serviceResult.setStatus(ServiceStatus.SUCCESS);
                serviceResult.setData(apartmentListByProvince);
            }
            return serviceResult;
        }


//        @Override
//        public ServiceResult updateApartment (Apartment apartment){
//            ServiceResult serviceResult = new ServiceResult();
//            if (!apartmentRepository.findById(apartment.getId()).isPresent()) {
//                serviceResult.setMessage("Apartment not found");
//            } else {
//                serviceResult.setData(apartmentRepository.save(apartment));
//            }
//            return serviceResult;
//        }
    }
}
