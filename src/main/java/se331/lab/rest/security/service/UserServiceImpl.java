package se331.lab.rest.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.repository.VaccineRepository;
import se331.lab.rest.security.entity.Authority;
import se331.lab.rest.security.entity.AuthorityName;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.AuthorityRepository;
import se331.lab.rest.security.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    VaccineRepository vaccineRepository;

    @Override
    public Page<User> getPatients(Integer page, Integer pageSize) {
        Authority authority = authorityRepository.findByName(AuthorityName.ROLE_PATIENT);
        return userRepository.findByAuthoritiesContaining(authority, PageRequest.of(page, pageSize));
    }

    @Override
    public Page<User> getDoctors(Integer page, Integer pageSize) {
        Authority authority = authorityRepository.findByName(AuthorityName.ROLE_DOCTOR);
        return userRepository.findByAuthoritiesContaining(authority, PageRequest.of(page, pageSize));
    }

    @Override
    public Page<User> all(Integer page, Integer pageSize) {
        return userRepository.findAll(PageRequest.of(page, pageSize));
    }

    @Override
    public Integer size() {
        return Math.toIntExact(userRepository.count());
    }

    @Override
    public Integer sizeOfPatient() {
        Authority authority = authorityRepository.findByName(AuthorityName.ROLE_PATIENT);
        return userRepository.countByAuthoritiesContaining(authority);
    }

    @Override
    public Integer sizeOfDoctor() {
        Authority authority = authorityRepository.findByName(AuthorityName.ROLE_DOCTOR);
        return userRepository.countByAuthoritiesContaining(authority);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User addVaccineToUser(Long id, Long vaccine_id) {
        User user = userRepository.findById(id).orElse(null);
        Vaccine vaccine = vaccineRepository.findById(vaccine_id).orElse(null);

        if(user == null || vaccine == null){
            return null;
        }

        user.getVaccines().add(vaccine);
        return userRepository.save(user);
    }
}
