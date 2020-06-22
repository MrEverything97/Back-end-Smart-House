package com.cg.smart_house.service.impl;


import com.cg.smart_house.model.*;
import com.cg.smart_house.repository.*;
import com.cg.smart_house.service.ApartmentService;
import com.cg.smart_house.service.ServiceResult;
import com.cg.smart_house.enumm.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.*;


@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired

    private HostRepository hostRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private OrdersRepository ordersRepository;



    @Override
    public ServiceResult createApartment(Apartment apartment) {
        ServiceResult serviceResult = new ServiceResult();

        // get and remove picture list from apartment
        List<Picture> pictureList = apartment.getPictures();
        apartment.setPictures(null);
        // get and remove address from apartment
        Address address = apartment.getAddress();
        apartment.setAddress(null);
        // save apartment without address and picture list
        Apartment newApartment = apartmentRepository.save(apartment);

        address.setApartment(apartment);
        addressRepository.save(address);

        pictureList.forEach(picture -> {
            picture.setApartment(newApartment);
            picture = pictureRepository.save(picture);
        });

        serviceResult.setMessage("add new apartment success");
        return serviceResult;
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
        //Tim Apartment By Host
        List<Host> hosts = hostRepository.findAllByApartment(apartment);
        for (Host host : hosts) {
            host.setApartment(null);
            hostRepository.save(host);
        }
        //Find Apartment By Address
        List<Picture> pictures = pictureRepository.findAllByApartment(apartment);
        for (Picture picture : pictures) {
            picture.setApartment(null);
            pictureRepository.save(picture);
        }
        Address address = addressRepository.findAllByApartment(apartment);
        address.setApartment(null);
        addressRepository.save(address);

        apartmentRepository.delete(apartment);
//        serviceResult.setData(apartment);

        return serviceResult;
    }

    @Override
    public ServiceResult findTopByPriceByDate(int price) {
        ServiceResult serviceResult = new ServiceResult();
        List<Apartment> apartments = apartmentRepository.findTop2ByPriceByDate(price);
        if (apartments.isEmpty()) {
            serviceResult.setMessage("No Apartment Math");
        }
        serviceResult.setData(apartmentRepository.findTop2ByPriceByDate(price));

        return serviceResult;
    }

    @Override
    public ServiceResult findAllByPriceByDate(int minPrice, int maxPrice) {
        ServiceResult serviceResult = new ServiceResult();
        List<Apartment> apartments = apartmentRepository.findAllByPriceByDateBetween(minPrice, maxPrice);
        if (apartments.isEmpty()) {
            serviceResult.setMessage("Not found");
        }
        serviceResult.setData(apartmentRepository.findAllByPriceByDateBetween(minPrice, maxPrice));
        return serviceResult;
    }

//    @Override
//    public ServiceResult findAllOrderByStartTimeAndEndTime(Date startTime, Date endTime) {
//        ServiceResult serviceResult = new ServiceResult();
//        List<Apartment> apartments = apartmentRepository.findAllByStartTimeAndEndTime(startTime, endTime);
//        if (apartments.isEmpty()) {
//            serviceResult.setMessage("Not found");
//        }
//        serviceResult.setData(apartmentRepository.findAllByStartTimeAndEndTime(startTime, endTime));
//
//        return serviceResult;
//    }

    @Override
    public ServiceResult findTop5ByPriceByDateAndNameContains(int price, String name) {
        ServiceResult serviceResult = new ServiceResult();
        List<Apartment> apartments = apartmentRepository.findTop5ByPriceByDateAndNameContains(price, name);
        if (apartments.isEmpty()) {
            serviceResult.setMessage("No Apartment Math");
        }
        serviceResult.setData(apartmentRepository.findTop5ByPriceByDateAndNameContains(price, name));
        return serviceResult;
    }

    @Override
    public ServiceResult findAllByHostId(Long hostId) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setStatus(ServiceStatus.FAILED);
        List<Apartment> apartmentList = apartmentRepository.findAllByHost_Id(hostId);
        if(apartmentList.isEmpty()){
            serviceResult.setMessage("NOT FOUND");
        } else {
            serviceResult.setMessage("SUCCESS");
            serviceResult.setData(apartmentList);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult searchApartment(int bedroom, int bathroom, Long province_id, int startPrice, int endPrice, Date startTime, Date endTime) {
        ServiceResult serviceResult = new ServiceResult();
        serviceResult.setStatus(ServiceStatus.FAILED);
        List<Apartment> apartmentList = apartmentRepository.findApartmentByBedroomAndBathroomAndAddress_Provinces_IdAndPriceByDateIsBetween(bedroom, bathroom, province_id, startPrice, endPrice);
        int i;
        List<Order> orderList;
        List<Apartment> resultApartmentList = new ArrayList<>();
        for(i = 0; i < apartmentList.size(); i++) {
            orderList = apartmentList.get(i).getOrders();
            Date nowDate = new Date();
            Collections.sort(orderList);
            int sizeList = orderList.size() - 1;
            boolean flag = true;
            for (int j = 0; j <= sizeList && flag; j++) {
                if (!((startTime.after(nowDate) && endTime.before(orderList.get(0).getStartTime()))
                        || startTime.after(orderList.get(sizeList).getEndTime())
                        || (startTime.after(orderList.get(j).getEndTime()) && endTime.before(orderList.get(j + 1).getStartTime())))){
                    flag = false;
                }
            }
            if(flag){
               resultApartmentList.add(apartmentList.get(i));
            }
        }
        if(!resultApartmentList.isEmpty()){
            serviceResult.setStatus(ServiceStatus.SUCCESS);
            serviceResult.setData(resultApartmentList);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult updateApartment(Long id,Apartment apartment) {
        // need new logic
        return null;
    }


    @Override
    public ServiceResult updateApartmentPicture(Long id, List<Picture> pictureList) { // only update pictures
        ServiceResult serviceResult = new ServiceResult();
        Apartment currentApartment = apartmentRepository.findById(id).orElse(null);
        if (currentApartment == null) {
            serviceResult.setStatus(ServiceStatus.FAILED);
            serviceResult.setMessage("Apartment Not Found");
        } else {
            List<Picture> oldPictureList = pictureRepository.findAllByApartment(currentApartment);
            pictureRepository.deleteAll(oldPictureList);
            for (Picture picture : pictureList) {
                picture.setId(null);
                picture.setApartment(currentApartment);
                pictureRepository.save(picture);
            }
        }
        //Find Apartment By Address
        return serviceResult;
    }


//    @Override
//    public ServiceResult searchAllByApartment(int bedroom, int bathroom, int min, int max, String address,
//                                              Date startTime, Date endTime) {
//        ServiceResult serviceResult = new ServiceResult();
//        List<Apartment> apartments = apartmentRepository.findAllByApartment( bedroom, bathroom, min, max, address, startTime, endTime);
//        if (apartments.isEmpty()) {
//            serviceResult.setMessage("Not found");
//        }
//        serviceResult.setData(apartmentRepository.findAllByApartment(bedroom, bathroom, min, max, address, startTime, endTime));
//        return serviceResult;
//    }
//        ServiceResult serviceResult = new ServiceResult();
//        if (!apartmentRepository.findById(apartment.getId()).isPresent()){
//            serviceResult.setMessage("Apartment not found");
//        } else{
//            serviceResult.setData(apartmentRepository.save(apartment));
//        }
//        return serviceResult;
//    }
}

