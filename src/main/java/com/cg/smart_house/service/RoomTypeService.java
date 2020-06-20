package com.cg.smart_house.service;

import com.cg.smart_house.model.RoomType;

public interface RoomTypeService {
    ServiceResult createRoomType(RoomType roomType);

    ServiceResult findAll();

    ServiceResult findById(Long id);

    ServiceResult deleteRoomType(Long id);

    ServiceResult updateRoomType(RoomType roomType);
}