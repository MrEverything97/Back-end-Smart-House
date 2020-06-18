package com.cg.smart_house.service.impl;

import com.cg.smart_house.dto.address.AddressDtoResponse;
import com.cg.smart_house.dto.apartment.ApartmentDtoRequest;
import com.cg.smart_house.dto.apartment.ApartmentDtoResponse;
import com.cg.smart_house.dto.picture.PictureDtoResponse;
import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Picture;
import com.cg.smart_house.repository.ApartmentRepository;
import com.cg.smart_house.repository.PictureRepository;
import com.cg.smart_house.service.ApartmentService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.service.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private PictureRepository pictureRepo;

    @Override
    public ApartmentDtoResponse createApartment(ApartmentDtoRequest request) {
        ApartmentDtoResponse apartmentResponse = new ApartmentDtoResponse();

        String name = request.getName();
        Integer bathroom = request.getBathroom();
        Integer bedroom = request.getBedroom();
        Integer priceByDate = request.getPriceByDate();
        String description = request.getDescription();
        Apartment apartment = new Apartment(name, bathroom, bedroom, priceByDate, description);

        apartmentResponse.setName(name);
        apartmentResponse.setBathroom(bathroom);
        apartmentResponse.setBedroom(bedroom);
        apartmentResponse.setPriceByDate(priceByDate);
        apartmentResponse.setDescription(description);

        //------------picture
        List<PictureDtoResponse> pictureDtoResponses = new ArrayList<>();
        List<Long> pictureIds = request.getPictureIds();
        for (Long id : pictureIds) {
            PictureDtoResponse pictureResponse = new PictureDtoResponse();
            Picture picture = pictureRepo.findById(id).orElseThrow(IllegalAccessError::new);
            Long pictureId = picture.getId();
            String imageUrl = picture.getImageUrl();
            pictureResponse.setId(pictureId);
            pictureResponse.setImageUrl(imageUrl);
            pictureDtoResponses.add(pictureResponse);
        }
        apartmentResponse.setPictureResponses(pictureDtoResponses);
        //-----------

        apartmentRepository.save(apartment);
        return apartmentResponse;
    }

    @Override
    public ServiceResult findAll() {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setData(apartmentRepository.findAll());
        return serviceResult;
    }

    @Override
    public ServiceResult findById(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Apartment apartment = apartmentRepository.findById(id).orElse(null);
        if (apartment == null) {
            serviceResult.setMessage("Apartment Not Found");
        }
        serviceResult.setData(apartment);
        return serviceResult;
    }

    @Override
    public ServiceResult deleteApartment(Long id) {
        ServiceResult serviceResult = new ServiceResult();
        Apartment apartment = apartmentRepository.findById(id).orElse(null);
        if (apartment == null) {
            serviceResult.setStatus(ServiceStatus.FAILED);
            serviceResult.setMessage("Apartment Not Found");
        }
        else {
            apartmentRepository.delete(apartment);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult updateApartment(Apartment apartment) {
        ServiceResult serviceResult = new ServiceResult();
        if (!apartmentRepository.findById(apartment.getId()).isPresent()){
            serviceResult.setMessage("Apartment not found");
        } else{
            serviceResult.setData(apartmentRepository.save(apartment));
        }
        return serviceResult;
    }
}
