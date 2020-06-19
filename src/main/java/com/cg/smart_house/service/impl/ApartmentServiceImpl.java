package com.cg.smart_house.service.impl;

import com.cg.smart_house.dto.address.AddressDtoResponse;
import com.cg.smart_house.dto.apartment.ApartmentDtoRequest;
import com.cg.smart_house.dto.apartment.ApartmentDtoResponse;
import com.cg.smart_house.dto.category.CategoryDtoResponse;
import com.cg.smart_house.dto.picture.PictureDtoResponse;
import com.cg.smart_house.dto.roomtype.RoomTypeDtoResponse;
import com.cg.smart_house.model.*;
import com.cg.smart_house.repository.*;
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

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    private AddressRepository addressRepository;

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

        //-----------Category
        List<CategoryDtoResponse> categoryDtoResponses = new ArrayList<>();
        List<Long> categoryIds = request.getCategoryIds();
        for (Long id : categoryIds) {
            CategoryDtoResponse categoryResponse = new CategoryDtoResponse();
            Category category = categoryRepository.findById(id).orElseThrow(IllegalAccessError::new);
            Long categoryId = category.getId();
            String categoryName = category.getName();
            categoryResponse.setId(categoryId);
            categoryResponse.setName(categoryName);
            categoryDtoResponses.add(categoryResponse);
        }
        apartmentResponse.setCategoryDtoResponses(categoryDtoResponses);

        //-----------Address
        AddressDtoResponse addressDtoResponse = new AddressDtoResponse();
        Long addressIds = request.getAddressId();
        Address address = addressRepository.findById(addressIds).orElseThrow(IllegalAccessError::new);
        addressDtoResponse.setId(address.getId());

        apartmentResponse.setAddressResponse(addressDtoResponse);

        //-----------Host
        Long hostIds = request.getHostId();
        apartmentResponse.setId(hostIds);

        //-----------RoomType
        List<RoomTypeDtoResponse> roomTypeDtoResponses = new ArrayList<>();
        List<Long> roomTypeIds = request.getRoomTypes();
        for (Long id : roomTypeIds) {
            RoomTypeDtoResponse roomTypeResponse = new RoomTypeDtoResponse();
            RoomType roomType = roomTypeRepository.findById(id).orElseThrow(IllegalAccessError::new);
            Long roomTypeId = roomType.getId();
            Integer type = roomType.getType();

            roomTypeResponse.setId(roomTypeId);
            roomTypeResponse.setType(type);

            roomTypeDtoResponses.add(roomTypeResponse);
        }
        apartmentResponse.setRoomTypeResponses(roomTypeDtoResponses);

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
