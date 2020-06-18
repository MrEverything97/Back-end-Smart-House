package com.cg.smart_house.service.impl;

import com.cg.smart_house.models.Apartment;
import com.cg.smart_house.models.RoomType;
import com.cg.smart_house.models.Status;
import com.cg.smart_house.repository.ApartmentRepository;
import com.cg.smart_house.repository.StatusRepository;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.ServiceStatus;
import com.cg.smart_house.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Override
    public ServiceResult createStatus(Status status) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(statusRepository.save(status));
        return serviceResult;
    }

    @Override
    public ServiceResult findAll() {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(statusRepository.findAll());
        return serviceResult;
    }

    @Override
    public ServiceResult findById(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Status status = statusRepository.findById(id).orElse(null);
        if (status == null) {
            serviceResult.setMessage("Status Not Found");
        }
        serviceResult.setData(status);
        return serviceResult;
    }

    @Override
    public ServiceResult deleteStatus(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Status status = statusRepository.findById(id).orElse(null);
        if (status == null) {
            serviceResult.setStatus(ServiceStatus.FAILED);
            serviceResult.setMessage("Status Not Found");
        } else {
            statusRepository.delete(status);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult updateStatus(Status status) {
        ServiceResult serviceResult = new ServiceResult();
        if (statusRepository.findById(status.getId()).isPresent()){
            serviceResult.setMessage("Status not found");
        } else{
            serviceResult.setData(statusRepository.save(status));
        }
        return serviceResult;
    }

    @Override
    public ServiceResult findAllStatusByApartment(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Optional<Apartment> apartment = apartmentRepository.findById(id);
        if (!apartment.isPresent()){
            serviceResult.setMessage("apartment not found");
            return serviceResult;
        } else {
            serviceResult.setData(statusRepository.findAllByApartment(apartment.get()));
        }
        return serviceResult;
    }


}
