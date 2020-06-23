package com.cg.smart_house.repository;

import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findAllByBedroom(int amount);
    List<Apartment> findAllByBathroom(int amount);
    List<Apartment> findAllByPictures(Picture picture);

//    List<Apartment> findAllByStatuses(Status status);
    Apartment findByName(String name);

    List<Apartment> findAllByCategories(Category category);

    List<Apartment> findAllByOrders(Order order);

    List<Apartment> findAllByAddress(Address address);

//    List<Apartment> findAllByHost(Host host);
//    List<Apartment> findAllByHost_Id(Long hostId);
    List<Apartment> findAllByRoomTypes(RoomType rooType);

    List<Apartment> findApartmentByBedroomAndBathroomAndAddress_Provinces_IdAndPriceByDateIsBetween(int bedroom,int bathroom,Long province_id,int startPrice,int endPrice);

//    List<Apartment> findTop2ByPriceByDate(int price);
//
//    List<Apartment> findTop5ByPriceByDateAndNameContains(int price, String name);
//
//    List<Apartment> findAllByPriceByDateBetween(int minPrice, int maxPrice);
}
