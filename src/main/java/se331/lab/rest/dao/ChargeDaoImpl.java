package se331.lab.rest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.rest.entity.Charge;
import se331.lab.rest.repository.ChargeRepository;

@Repository
public class ChargeDaoImpl implements ChargeDao{
    @Autowired
    ChargeRepository chargeRepository;

    @Override
    public Charge save(Charge charge) {
        return chargeRepository.save(charge);
    }

    @Override
    public Page<Charge> all(Pageable pageable) {
        return chargeRepository.findAll(pageable);
    }

    @Override
    public Integer size() {
        return Math.toIntExact(chargeRepository.count());
    }

    @Override
    public Page<Charge> findByDoctorUsername(String username, Pageable pageable) {
        return chargeRepository.findByDoctorUsername(username, pageable);
    }

    @Override
    public Integer sizeByDoctorUsername(String username) {
        return chargeRepository.countByDoctorUsername(username);
    }
}
