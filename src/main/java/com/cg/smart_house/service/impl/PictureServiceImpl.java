package com.cg.smart_house.service.impl;

import com.cg.smart_house.model.Picture;
import com.cg.smart_house.repository.PictureRepository;
import com.cg.smart_house.service.PictureService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {
    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public ServiceResult createPicture(Picture picture) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(pictureRepository.save(picture));
        return serviceResult;
    }

    @Override
    public ServiceResult findAll() {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(pictureRepository.findAll());
        return serviceResult;
    }

    @Override
    public ServiceResult findById(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Picture picture = pictureRepository.findById(id).orElse(null);
        if (picture == null) {
            serviceResult.setMessage("Picture Not Found");
        }
        serviceResult.setData(picture);
        return serviceResult;
    }

    @Override
    public ServiceResult deletePicture(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Picture picture = pictureRepository.findById(id).orElse(null);
        if (picture == null) {
            serviceResult.setStatus(ServiceStatus.FAILED);
            serviceResult.setMessage("Picture Not Found");
        } else {
            pictureRepository.delete(picture);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult updatePicture(Picture picture) {
        ServiceResult serviceResult = new ServiceResult();
        if (! pictureRepository.findById(picture.getId()).isPresent()) {
            serviceResult.setMessage("Picture not found");
        } else {
            serviceResult.setData(pictureRepository.save(picture));
        }
        return serviceResult;
    }
}
