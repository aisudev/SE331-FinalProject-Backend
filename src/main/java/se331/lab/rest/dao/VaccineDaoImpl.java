package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.repository.VaccineRepository;

@Repository
public class VaccineDaoImpl implements VaccineDao {
    @Autowired
    VaccineRepository vaccineRepository;

    @Override
    public Vaccine save(Vaccine vaccine) {
        return vaccineRepository.save(vaccine);
    }

    @Override
    public Page<Vaccine> all(Pageable pageable) {
        return vaccineRepository.findAll(pageable);
    }

    @Override
    public Vaccine get(Long id) {
        return vaccineRepository.findById(id).orElse(null);
    }

    @Override
    public Integer size() {
        return Math.toIntExact(vaccineRepository.count());
    }
}
