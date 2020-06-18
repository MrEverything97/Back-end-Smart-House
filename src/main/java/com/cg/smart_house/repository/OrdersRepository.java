package com.cg.smart_house.repository;

import com.cg.smart_house.models.Apartment;
import com.cg.smart_house.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findAllByApartment(Apartment apartment);
 }
