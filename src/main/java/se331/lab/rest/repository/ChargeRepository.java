package se331.lab.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Charge;

public interface ChargeRepository extends JpaRepository<Charge, Long> {
    Page<Charge> findByDoctorUsername(String username, Pageable pageable);
    Integer countByDoctorUsername(String username);
    Page<Charge> findAll(Pageable pageable);
}
