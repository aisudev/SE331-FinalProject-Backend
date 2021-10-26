package se331.lab.rest.entity;

import lombok.*;
import se331.lab.rest.security.entity.User;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Charge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String doctorUsername;
    @OneToOne
    User user;
}
