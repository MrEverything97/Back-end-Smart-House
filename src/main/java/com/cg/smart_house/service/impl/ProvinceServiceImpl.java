package com.cg.smart_house.service.impl;

import com.cg.smart_house.models.Province;
import com.cg.smart_house.repository.ProvinceRepository;
import com.cg.smart_house.service.ProvinceService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceServiceImpl implements ProvinceService {
    @Autowired
    private ProvinceRepository provinceRepository;

    @Override
    public ServiceResult createProvince(Province province) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(provinceRepository.save(province));
        return serviceResult;
    }

    @Override
    public ServiceResult findAll() {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(provinceRepository.findAll());
        return serviceResult;
    }

    @Override
    public ServiceResult findById(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Province province = provinceRepository.findById(id).orElse(null);
        if (province == null) {
            serviceResult.setMessage("Province Not Found");
        }
        serviceResult.setData(province);
        return serviceResult;
    }

    @Override
    public ServiceResult deleteProvince(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Province province = provinceRepository.findById(id).orElse(null);
        if (province == null) {
            serviceResult.setStatus(ServiceStatus.FAILED);
            serviceResult.setMessage("Province Not Found");
        } else {
            provinceRepository.delete(province);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult updateProvince(Province province) {
        ServiceResult serviceResult = new ServiceResult();
        if (provinceRepository.findById(province.getId()).isPresent()) {
            serviceResult.setMessage("Province not found");
        } else {
            serviceResult.setData(provinceRepository.save(province));
        }
        return serviceResult;
    }
}
