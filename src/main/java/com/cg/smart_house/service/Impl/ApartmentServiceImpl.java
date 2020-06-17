package com.cg.smart_house.service.Impl;

import com.cg.smart_house.models.Apartment;
import com.cg.smart_house.repository.ApartmentRepository;
import com.cg.smart_house.service.ApartmentService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.ServiceStatus;
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
}
