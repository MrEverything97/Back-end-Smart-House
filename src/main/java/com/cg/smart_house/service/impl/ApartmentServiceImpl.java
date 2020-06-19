package com.cg.smart_house.service.impl;

import com.cg.smart_house.model.Address;
import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Picture;
import com.cg.smart_house.model.RoomType;
import com.cg.smart_house.repository.AddressRepository;
import com.cg.smart_house.repository.ApartmentRepository;
import com.cg.smart_house.repository.PictureRepository;
import com.cg.smart_house.repository.RoomTypeRepository;
import com.cg.smart_house.service.ApartmentService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public ServiceResult createApartment(Apartment apartment) {
        ServiceResult serviceResult = new ServiceResult();
        List<RoomType> roomTypes = apartment.getRoomTypes();
        Address address = apartment.getAddress();
        addressRepository.save(address);
        List<Picture> pictureList = apartment.getPictures();
        pictureList.forEach(picture -> {
            pictureRepository.save(picture);
        });

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
        }
        else {
            apartmentRepository.delete(apartment);
        }
        return serviceResult;
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
