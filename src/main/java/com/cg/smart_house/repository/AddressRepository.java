package com.cg.smart_house.repository;


import com.cg.smart_house.model.Address;
import com.cg.smart_house.model.Apartment;
import com.cg.smart_house.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

import java.util.List;


public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByName(String nameAddress);

    Address findAllByApartment(Apartment apartment);

=======
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByName(String nameAddress);

>>>>>>> 5c756754e611cad23b6af99de1cf0587f25e6bc4
}

