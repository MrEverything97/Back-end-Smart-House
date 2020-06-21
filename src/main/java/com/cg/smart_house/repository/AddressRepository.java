package com.cg.smart_house.repository;


import com.cg.smart_house.model.Address;
import com.cg.smart_house.model.Province;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByName(String nameAddress);

    Address findAllByProvinces(Optional<Province> province);

}

