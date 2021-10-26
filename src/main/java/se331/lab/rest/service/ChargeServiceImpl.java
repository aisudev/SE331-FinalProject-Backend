package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.ChargeDao;
import se331.lab.rest.entity.Charge;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.UserRepository;

@Service
public class ChargeServiceImpl implements ChargeService{
    @Autowired
    ChargeDao chargeDao;

    @Autowired
    UserRepository userRepository;

    @Override
    public Charge save(String username, Long id) {
        User user = userRepository.findById(id).orElse(null);
        User doctor = userRepository.findByUsername(username);
        if(user == null || doctor == null){
            return null;
        }

        Charge charge = Charge.builder().doctorUsername(username).user(user).build();

        return chargeDao.save(charge);
    }

    @Override
    public Page<Charge> all(Integer page, Integer pageSize) {
        return chargeDao.all(PageRequest.of(page, pageSize));
    }

    @Override
    public Integer size() {
        return chargeDao.size();
    }

    @Override
    public Page<Charge> findByDoctorUsername(String username, Integer page, Integer pageSize) {
        return chargeDao.findByDoctorUsername(username, PageRequest.of(page, pageSize));
    }

    @Override
    public Integer sizeByDoctorUsername(String username) {
        return null;
    }
}
