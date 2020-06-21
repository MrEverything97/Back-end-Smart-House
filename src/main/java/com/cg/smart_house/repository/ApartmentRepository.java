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

    List<Apartment> findAllByHosts(Host host);

    List<Apartment> findAllByRoomTypes(RoomType rooType);

    List<Apartment> findAllByAddressProvinces(Province province);

    List<Apartment> findAllByBedroomAndBathroomAndPriceByDateAndAddress_Provinces(int bedroom, int bathroom, int priceByDate,Province province);
}

//    @Query("select apart,adr,ord from Apartment apart, Address adr, Order ord "
//            + "where apart.id = adr.apartment.id and apart.id = ord.apartment.id " +
//            "and adr.provinces=:nameProvince and :minTime <= ord.startTime and ord.endTime <= :maxTime ")
//    List<Apartment> findAllByAddressAndOrderStartTimeAndEndTime(@Param("idProvince") Long idProvince,
//                                                                @Param("minTime") Date minTime,
//                                                                @Param("maxTime") Date maxTime);
//}
