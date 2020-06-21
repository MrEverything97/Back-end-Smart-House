package com.cg.smart_house.repository;


import com.cg.smart_house.model.Apartment;

import com.cg.smart_house.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    List<Host> findAllByApartment(Apartment apartment);

}
