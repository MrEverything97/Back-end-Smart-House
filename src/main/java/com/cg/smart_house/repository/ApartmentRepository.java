package com.cg.smart_house.repository;

import com.cg.smart_house.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findAllByUser_Id(Long userId);

    List<Apartment> findApartmentByBedroomAndBathroomAndAddress_Province_IdAndPriceByDateIsBetween(int bedroom,int bathroom,Long province_id,int startPrice,int endPrice);

    List<Apartment> findTop2ByPriceByDate(int price);

    List<Apartment> findTop5ByPriceByDateAndNameContains(int price, String name);

    List<Apartment> findAllByPriceByDateBetween(int minPrice, int maxPrice);

}
