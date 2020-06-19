package com.cg.smart_house.repository;

import com.cg.smart_house.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findAllByPictures(Picture picture);

<<<<<<< HEAD
=======
//    List<Apartment> findAllByStatuses(Status status);
    Apartment findByName(String name);

>>>>>>> dev.loi
    List<Apartment> findAllByCategories(Category category);

    List<Apartment> findAllByOrders(Order order);

    List<Apartment> findAllByAddress(Address address);

    List<Apartment> findAllByHosts(Host host);

    List<Apartment> findAllByRoomTypes(RoomType rooType);
}