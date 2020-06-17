package com.cg.smart_house.service;

import com.cg.smart_house.models.Province;
import com.cg.smart_house.models.RoomType;

public interface ProvinceService {

    ServiceResult createProvince(Province province);

    ServiceResult findAll();

    ServiceResult findById(Long id);

    ServiceResult deleteProvince(Long id);

    ServiceResult updateProvince(Province province);
}
