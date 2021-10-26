package se331.lab.rest.security.service;

import org.springframework.data.domain.Page;
import se331.lab.rest.security.controller.UpdateUserForm;
import se331.lab.rest.security.entity.User;

public interface UserService {
    Page<User> getPatients(Integer page, Integer pageSize);
    Page<User> getDoctors(Integer page, Integer pageSize);
    Integer sizeOfPatient();
    Integer sizeOfDoctor();
    Page<User> all(Integer page, Integer pageSize);
    Integer size();
    User get(Long id);
    User addVaccineToUser(Long id, Long vaccine_id);
    User updateComment(Long id, String comment);
    User roleToAdmin(Long id);
    User roleToDoctor(Long id);
    User roleToPatient(Long id);
    User updateUser(Long id, UpdateUserForm form);
}
