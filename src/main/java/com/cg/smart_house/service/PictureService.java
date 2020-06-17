package com.cg.smart_house.service;

import com.cg.smart_house.models.Picture;
import com.cg.smart_house.models.Province;

public interface PictureService {

    ServiceResult createPicture(Picture picture);

    ServiceResult findAll();

    ServiceResult findById(Long id);

    ServiceResult deletePicture(Long id);

    ServiceResult updatePicture(Picture picture);
}
