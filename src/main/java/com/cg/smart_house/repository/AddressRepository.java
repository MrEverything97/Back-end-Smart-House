package com.cg.smart_house.repository;

import com.cg.smart_house.model.Address;
import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<<<< Temporary merge branch 1
import org.springframework.stereotype.Repository;

@Repository
=========

>>>>>>>>> Temporary merge branch 2
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByName(String nameAddress);
    Address findAllByApartment(Apartment apartment);
}
