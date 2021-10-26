package se331.lab.rest.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Vaccine;

public interface VaccineDao {
    Vaccine save(Vaccine vaccine);
    Page<Vaccine> all(Pageable pageable);
    Vaccine get(Long id);
    Integer size();
}
