package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Charge;

public interface ChargeDao {
    Charge save(Charge charge);
    Page<Charge> all(Pageable pageable);
    Integer size();
    Page<Charge> findByDoctorUsername(String username, Pageable pageable);
    Integer sizeByDoctorUsername(String username);
}
