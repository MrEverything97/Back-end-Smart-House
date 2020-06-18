package com.cg.smart_house.repository;

import com.cg.smart_house.models.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
    List<Apartment> findAllByBedroom(int amount);
    List<Apartment> findAllByBathroom(int amount);
}
