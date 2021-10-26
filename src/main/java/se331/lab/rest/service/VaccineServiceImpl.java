package se331.lab.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import se331.lab.rest.dao.VaccineDao;
import se331.lab.rest.entity.Vaccine;

@Service
public class VaccineServiceImpl implements VaccineService{
    @Autowired
    VaccineDao vaccineDao;

    @Override
    public Page<Vaccine> all(Integer page, Integer pageSize) {
        return vaccineDao.all(PageRequest.of(page, pageSize));
    }

    @Override
    public Vaccine get(Long id) {
        return vaccineDao.get(id);
    }

    @Override
    public Integer size() {
        return vaccineDao.size();
    }
}
