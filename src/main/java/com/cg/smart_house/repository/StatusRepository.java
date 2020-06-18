package com.cg.smart_house.repository;

import com.cg.smart_house.models.Apartment;
import com.cg.smart_house.models.Status;
import com.cg.smart_house.service.ServiceResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    List<Status> findAllByApartment(Apartment apartment);
}
