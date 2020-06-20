package com.cg.smart_house.repository;

<<<<<<< HEAD
import com.cg.smart_house.model.Apartment;
=======
>>>>>>> 5c756754e611cad23b6af99de1cf0587f25e6bc4
import com.cg.smart_house.model.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
import java.util.List;

@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
    List<Host> findAllByApartment(Apartment apartment);
=======
@Repository
public interface HostRepository extends JpaRepository<Host, Long> {
>>>>>>> 5c756754e611cad23b6af99de1cf0587f25e6bc4
}
