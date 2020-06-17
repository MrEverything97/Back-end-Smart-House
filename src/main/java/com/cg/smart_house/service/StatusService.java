package com.cg.smart_house.service;

import com.cg.smart_house.models.RoomType;
import com.cg.smart_house.models.Status;

public interface StatusService {
    ServiceResult createStatus(Status status);

    ServiceResult findAll();

    ServiceResult findById(Long id);

    ServiceResult deleteStatus(Long id);

    ServiceResult updateStatus(Status status);
}
