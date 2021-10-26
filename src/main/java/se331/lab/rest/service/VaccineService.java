package se331.lab.rest.service;

import org.springframework.data.domain.Page;
import se331.lab.rest.entity.Vaccine;

public interface VaccineService {
    Page<Vaccine> all(Integer page, Integer pageSize);
    Vaccine get(Long id);
    Integer size();
}
