package com.cg.smart_house.service.Impl;

import com.cg.smart_house.models.Apartment;
import com.cg.smart_house.repository.ApartmentRepository;
import com.cg.smart_house.service.ApartmentService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Override
    public ServiceResult createApartment(Apartment apartment) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(apartmentRepository.save(apartment));
        return serviceResult;
    }

    @Override
    public ServiceResult updateApartment(Apartment apartment) {
        ServiceResult serviceResult = new ServiceResult();
        if (apartmentRepository.findById(apartment.getId()).isPresent()){
            serviceResult.setMessage("Apartment not found");
        } else{
            serviceResult.setData(apartmentRepository.save(apartment));
        }
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
        if(apartment == null)
        {
            serviceResult.setMessage("Apartment not found");
        }
        serviceResult.setData(apartment);
        return serviceResult;
    }
}
