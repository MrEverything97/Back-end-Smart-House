package com.cg.smart_house.repository;


import com.cg.smart_house.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AddressRepository extends JpaRepository<Address, Long> {

}

