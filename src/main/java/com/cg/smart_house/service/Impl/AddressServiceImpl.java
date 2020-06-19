package com.cg.smart_house.service.Impl;

import com.cg.smart_house.model.Address;
import com.cg.smart_house.repository.AddressRepository;
import com.cg.smart_house.service.AddressService;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Override
    public ServiceResult createAddress(Address address) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(addressRepository.save(address));
        return serviceResult;
    }

    @Override
    public ServiceResult findAll() {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(addressRepository.findAll());
        return serviceResult;
    }
}
