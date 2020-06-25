package com.cg.smart_house.service.impl;

import com.cg.smart_house.model.Address;
import com.cg.smart_house.repository.AddressRepository;
import com.cg.smart_house.service.AddressService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.enumm.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

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

    @Override
    public ServiceResult findById(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Address address = addressRepository.findById(id).orElse(null);
        if (address == null) {
            serviceResult.setMessage("Address Not Found");
        }
        serviceResult.setData(address);
        return serviceResult;
    }

    @Override
    public ServiceResult deleteAddress(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Address address = addressRepository.findById(id).orElse(null);
        if (address == null) {
            serviceResult.setStatus(ServiceStatus.FAILED);
            serviceResult.setMessage("Address Not Found");
        }
        else {
            addressRepository.delete(address);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult updateAddress(Address address) {
        ServiceResult serviceResult = new ServiceResult();
        if (!addressRepository.findById(address.getId()).isPresent()){
            serviceResult.setMessage("Address not found");
        } else{
            serviceResult.setData(addressRepository.save(address));
        }
        return serviceResult;
    }
}
