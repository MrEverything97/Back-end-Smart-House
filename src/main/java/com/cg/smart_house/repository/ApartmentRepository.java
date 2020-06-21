package com.cg.smart_house.repository;

import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

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
    List<Apartment> findAllByHost(Host host);
    List<Apartment> findAllByRoomTypes(RoomType rooType);

    List<Apartment> findTop2ByPriceByDate(int price);

    List<Apartment> findTop5ByPriceByDateAndNameContains(int price, String name);

    List<Apartment> findAllByPriceByDateBetween(int minPrice, int maxPrice);

    @Query(value = "select o.start_time, o.end_time from apartment inner join orders o on apartment.id = o.apartment_id", nativeQuery = true)
    List<Apartment> findAllByStartTimeAndEndTime(Date startTime, Date endTime);

    @Query(value = "select ap.name, ap.bedroom, ap.bathroom, ap.price_by_date, a.name, o.start_time, o.end_time\n" +
            "from apartment ap\n" +
            "    inner join address a on ap.id = a.apartment_id\n" +
            "    inner join `order` o on ap.id = o.apartment_id;\n", nativeQuery = true)
    List<Apartment> findAllByApartment(@Param("name") String name,
                                       @Param("bedroom") int bedroom,
                                       @Param("bathroom") int bathroom,
                                       @Param("price") int price,
                                       @Param("address") String address,
                                       @Param("start") Date startTime,
                                       @Param("end") Date endTime
                                       );

}
