package se331.lab.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

}
