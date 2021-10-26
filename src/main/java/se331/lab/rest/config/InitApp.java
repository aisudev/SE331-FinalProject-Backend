package se331.lab.rest.config;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.lab.rest.dao.ChargeDao;
import se331.lab.rest.dao.VaccineDao;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.security.entity.Authority;
import se331.lab.rest.security.entity.AuthorityName;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.repository.AuthorityRepository;
import se331.lab.rest.security.repository.UserRepository;
import se331.lab.rest.service.ChargeService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    AuthorityRepository authorityRepository;
    @Autowired
    UserRepository userRepository;
    User user1, user2, user3;

    @Autowired
    VaccineDao vaccineDao;

    @Autowired
    ChargeService chargeService;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Vaccine pfier = Vaccine.builder().name("Pfier").build();
        Vaccine moderna = Vaccine.builder().name("Moderna").build();
        Vaccine sinovac = Vaccine.builder().name("Sinovac").build();
        Vaccine sinopharm = Vaccine.builder().name("Sinopharm").build();
        vaccineDao.save(pfier);
        vaccineDao.save(moderna);
        vaccineDao.save(sinopharm);
        vaccineDao.save(sinovac);

        List<Vaccine> vaccines = new ArrayList<>();
        vaccines.add(pfier);
        vaccines.add(moderna);

        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authPatient = Authority.builder().name(AuthorityName.ROLE_PATIENT).build();
        Authority authAdmin = Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
        Authority authDoctor = Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .address("")
                .age(20)
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user2 = User.builder()
                .username("doctor")
                .password(encoder.encode("doctor"))
                .firstname("doctor")
                .lastname("doctor")
                .address("cnx")
                .age(20)
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user3 = User.builder()
                .username("user1")
                .password(encoder.encode("user1"))
                .firstname("somsri")
                .lastname("srisom")
                .address("cnx")
                .age(20)
                .vaccines(vaccines)
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        User user4 = User.builder()
                .username("user2")
                .password(encoder.encode("user2"))
                .firstname("sommai")
                .lastname("maisom")
                .address("cnx")
                .age(21)
                .vaccines(vaccines)
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        User user5 = User.builder()
                .username("user3")
                .password(encoder.encode("user3"))
                .firstname("somsak")
                .lastname("saksom")
                .address("cnx")
                .age(21)
                .vaccines(vaccines)
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        User user6 = User.builder()
                .username("user4")
                .password(encoder.encode("user4"))
                .firstname("sompong")
                .lastname("pongsom")
                .address("cnx")
                .age(22)
                .vaccines(vaccines)
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        User user7 = User.builder()
                .username("user5")
                .password(encoder.encode("user5"))
                .firstname("somkiat")
                .lastname("kiatsom")
                .address("cnx")
                .age(21)
                .vaccines(vaccines)
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        User user8 = User.builder()
                .username("user6")
                .password(encoder.encode("user6"))
                .firstname("samsri")
                .lastname("srisam")
                .address("cnx")
                .age(21)
                .vaccines(vaccines)
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2021, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        authorityRepository.save(authPatient);
        authorityRepository.save(authAdmin);
        authorityRepository.save(authDoctor);

        user1.getAuthorities().add(authAdmin);
        user2.getAuthorities().add(authDoctor);
        user3.getAuthorities().add(authPatient);
        user4.getAuthorities().add(authPatient);
        user5.getAuthorities().add(authPatient);
        user6.getAuthorities().add(authPatient);
        user7.getAuthorities().add(authPatient);
        user8.getAuthorities().add(authPatient);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
        userRepository.save(user8);

        chargeService.save("doctor", 3L);
        chargeService.save("doctor", 4L);
        chargeService.save("doctor", 5L);
        chargeService.save("doctor", 6L);
        chargeService.save("doctor", 7L);
        chargeService.save("doctor", 8L);

    }
}
