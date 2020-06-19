package com.cg.smart_house.service;

import com.cg.smart_house.dto.apartment.ApartmentDtoRequest;
import com.cg.smart_house.dto.apartment.ApartmentDtoResponse;
import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Host;

public interface HostService {
    ServiceResult createHost(Host host);

    ServiceResult findAll();

    ServiceResult findById(Long id);

    ServiceResult deleteHost(Long id);

    ServiceResult updateHost(Host host);
}
