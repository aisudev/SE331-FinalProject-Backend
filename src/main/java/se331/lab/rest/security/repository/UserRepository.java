package se331.lab.rest.security.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.security.entity.Authority;
import se331.lab.rest.security.entity.AuthorityName;
import se331.lab.rest.security.entity.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Page<User> findByAuthoritiesContaining(Authority authorities, Pageable pageable);
    Integer countByAuthoritiesContaining(Authority authority);
    Page<User> findAll(Pageable pageable);
}
