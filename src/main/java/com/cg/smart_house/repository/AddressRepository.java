package com.cg.smart_house.repository;

import com.cg.smart_house.model.Address;
import com.cg.smart_house.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAllByApartment(Apartment apartment);
}
