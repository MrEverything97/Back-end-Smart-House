package com.cg.smart_house.service.impl;


import com.cg.smart_house.model.*;
import com.cg.smart_house.repository.*;
import com.cg.smart_house.service.ApartmentService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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

//    private Picture savePicture();

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

        return serviceResult;
    }

    @Override
    public ServiceResult findAllByHostId(Long hostId) {
        ServiceResult serviceResult = new ServiceResult();
        List<Apartment> apartmentList = apartmentRepository.findAllByHost_Id(hostId);
        if(apartmentList.isEmpty()){
            serviceResult.setMessage("NOT FOUND");
        } else {
            serviceResult.setMessage("SUCCESS");
            serviceResult.setData(apartmentList);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult updateApartment(Long id,Apartment apartment) {
        // need new logic
        return null;
    }


    @Override
    public ServiceResult updateApartmentPicture(Long id, List<Picture> pictureList) { // only update pictures
        ServiceResult serviceResult = new ServiceResult();
        Apartment currentApartment = apartmentRepository.findById(id).orElse(null);
        if (currentApartment == null) {
            serviceResult.setStatus(ServiceStatus.FAILED);
            serviceResult.setMessage("Apartment Not Found");
        } else {
            List<Picture> oldPictureList = pictureRepository.findAllByApartment(currentApartment);
            pictureRepository.deleteAll(oldPictureList);
            for (Picture picture : pictureList) {
                picture.setId(null);
                picture.setApartment(currentApartment);
                pictureRepository.save(picture);
            }
        }
        //Find Apartment By Address
        return serviceResult;
    }
}
