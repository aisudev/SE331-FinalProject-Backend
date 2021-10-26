package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Charge;

public interface ChargeService {
    Charge save(String username, Long id);
    Page<Charge> all(Integer page, Integer pageSize);
    Integer size();
    Page<Charge> findByDoctorUsername(String username, Integer page, Integer pageSize);
    Integer sizeByDoctorUsername(String username);
}
