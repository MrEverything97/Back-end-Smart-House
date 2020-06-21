package com.cg.smart_house.service.impl;


import com.cg.smart_house.model.*;
import com.cg.smart_house.repository.*;
import com.cg.smart_house.service.ApartmentService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired

    private HostRepository hostRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    ProvinceRepository provinceRepository;



    @Override
    public ServiceResult createApartment(Apartment apartment) {
        ServiceResult serviceResult = new ServiceResult();

        // get and remove picture list from apartment
        List<Picture> pictureList = apartment.getPictures();
        apartment.setPictures(null);
        // get and remove address from apartment
        Address address = apartment.getAddress();
        apartment.setAddress(null);
        // save apartment without address and picture list
        Apartment newApartment = apartmentRepository.save(apartment);

        address.setApartment(apartment);
        addressRepository.save(address);

        pictureList.forEach(picture -> {
            picture.setApartment(newApartment);
            picture = pictureRepository.save(picture);
        });

        serviceResult.setMessage("add new apartment success");
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
        //Tim Apartment By Host
        List<Host> hosts = hostRepository.findAllByApartment(apartment);
        for (Host host : hosts) {
            host.setApartment(null);
            hostRepository.save(host);
        }
        //Find Apartment By Address
        List<Picture> pictures = pictureRepository.findAllByApartment(apartment);
        for (Picture picture : pictures) {
            picture.setApartment(null);
            pictureRepository.save(picture);
        }
        Address address = addressRepository.findAllByApartment(apartment);
        address.setApartment(null);
        addressRepository.save(address);

        apartmentRepository.delete(apartment);
//        serviceResult.setData(apartment);

        return serviceResult;
    }

    @Override
    public ServiceResult findTopByPriceByDate(int price) {
        ServiceResult serviceResult = new ServiceResult();
        List<Apartment> apartments = apartmentRepository.findTop2ByPriceByDate(price);
        if (apartments.isEmpty()) {
            serviceResult.setMessage("No Apartment Math");
        }
        serviceResult.setData(apartmentRepository.findTop2ByPriceByDate(price));
        return serviceResult;
    }

    @Override
    public ServiceResult findTop5ByPriceByDateAndNameContains(int price, String name) {
        ServiceResult serviceResult = new ServiceResult();
        List<Apartment> apartments = apartmentRepository.findTop5ByPriceByDateAndNameContains(price, name);
        if (apartments.isEmpty()) {
            serviceResult.setMessage("No Apartment Math");
        }
        serviceResult.setData(apartmentRepository.findTop5ByPriceByDateAndNameContains(price, name));
        return serviceResult;
    }

    @Override
    public ServiceResult findAllByPriceByDate(int minPrice, int maxPrice) {
        ServiceResult serviceResult = new ServiceResult();
        List<Apartment> apartments = apartmentRepository.findAllByPriceByDateBetween(minPrice, maxPrice);
        if (apartments.isEmpty()) {
            serviceResult.setMessage("Not found");
        }
        serviceResult.setData(apartmentRepository.findAllByPriceByDateBetween(minPrice, maxPrice));
        return serviceResult;
    }

    @Override
    public ServiceResult findAllOrderByStartTimeAndEndTime(Date startTime, Date endTime) {
        return null;
    }

//    @Override
//    public ServiceResult findAllOrderByStartTimeAndEndTime(Date startTime, Date endTime) {
//        ServiceResult serviceResult = new ServiceResult();
//        List<Apartment> apartments = apartmentRepository.findAllByStartTimeAndEndTime(startTime, endTime);
//        if (apartments.isEmpty()) {
//            serviceResult.setMessage("Not found");
//        }
//        serviceResult.setData(apartmentRepository.findAllByStartTimeAndEndTime(startTime, endTime));
//        return serviceResult;
//    }


//    @Override
//    public Apartment saveApartment(Apartment apartment) {
//        return null;
//    }
//
//    @Override
//    public List<Picture> savePictures(Apartment apartmentObj, Apartment apartment) {
//        return null;
//    }

    @Override
    public ServiceResult updateApartment(Long id,Apartment apartment) {
        ServiceResult serviceResult = new ServiceResult();
        Apartment currentApartment = apartmentRepository.findById(id).orElse(null);
        if (apartment == null) {
            serviceResult.setStatus(ServiceStatus.FAILED);
            serviceResult.setMessage("Apartment Not Found");
        } else {

            List<Picture> oldPictures = pictureRepository.findAllByApartment(currentApartment);
            pictureRepository.deleteAll(oldPictures);
            List<Picture> newPictures = apartment.getPictures();
            for (Picture picture : newPictures) {
                picture.setId(null);
                picture.setApartment(currentApartment);
                pictureRepository.save(picture);
            }
        }
        //Find Apartment By Address
        return serviceResult;
    }

    @Override
    public ServiceResult searchAllByApartment(String name, int bedroom, int bathroom, int price, String address,
                                              Date startTime, Date endTime) {
        ServiceResult serviceResult = new ServiceResult();
        List<Apartment> apartments = apartmentRepository.findAllByApartment(name, bedroom, bathroom, price, address, startTime, endTime);
        if (apartments.isEmpty()) {
            serviceResult.setMessage("Not found");
        }
        serviceResult.setData(apartmentRepository.findAllByApartment(name, bedroom, bathroom, price, address, startTime, endTime));
        return serviceResult;
    }
//        ServiceResult serviceResult = new ServiceResult();
//        if (!apartmentRepository.findById(apartment.getId()).isPresent()){
//            serviceResult.setMessage("Apartment not found");
//        } else{
//            serviceResult.setData(apartmentRepository.save(apartment));
//        }
//        return serviceResult;
//    }
}