package com.cg.smart_house.service.impl;

import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.RoomType;
import com.cg.smart_house.repository.ApartmentRepository;
import com.cg.smart_house.repository.RoomTypeRepository;
import com.cg.smart_house.service.RoomTypeService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Override
    public ServiceResult createRoomType(RoomType roomType) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(roomTypeRepository.save(roomType));
        return serviceResult;
    }

    @Override
    public ServiceResult findAll() {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(roomTypeRepository.findAll());
        return serviceResult;
    }

    @Override
    public ServiceResult findById(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        RoomType roomType = roomTypeRepository.findById(id).orElse(null);
        if (roomType == null) {
            serviceResult.setMessage("RoomType Not Found");
        }
        serviceResult.setData(roomType);
        return serviceResult;
    }

    @Override
    public ServiceResult deleteRoomType(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        RoomType roomType = roomTypeRepository.findById(id).orElse(null);
        if (roomType == null) {
            serviceResult.setStatus(ServiceStatus.FAILED);
            serviceResult.setMessage("RoomType Not Found");
        } else {
            roomTypeRepository.delete(roomType);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult updateRoomType(RoomType roomType) {
        ServiceResult serviceResult = new ServiceResult();
        if (roomTypeRepository.findById(roomType.getId()).isPresent()){
            serviceResult.setMessage("RoomType not found");
        } else{
            serviceResult.setData(roomTypeRepository.save(roomType));
        }
        return serviceResult;
    }
}
