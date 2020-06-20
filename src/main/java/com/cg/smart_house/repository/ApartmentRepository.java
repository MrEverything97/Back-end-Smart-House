package com.cg.smart_house.repository;

import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findAllByBedroom(int amount);
    List<Apartment> findAllByBathroom(int amount);
    List<Apartment> findAllByPictures(Picture picture);
<<<<<<< HEAD
    Apartment findByName(String name);
=======

//    List<Apartment> findAllByStatuses(Status status);
    Apartment findByName(String name);


>>>>>>> 5c756754e611cad23b6af99de1cf0587f25e6bc4
    List<Apartment> findAllByCategories(Category category);
    List<Apartment> findAllByOrders(Order order);
    List<Apartment> findAllByAddress(Address address);
    List<Apartment> findAllByHost(Host host);
    List<Apartment> findAllByRoomTypes(RoomType rooType);

    List<Apartment> findTopByPriceByDate(int price);
}
