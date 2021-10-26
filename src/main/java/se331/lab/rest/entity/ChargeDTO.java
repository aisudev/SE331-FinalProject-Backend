package se331.lab.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se331.lab.rest.security.entity.UserDTO;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeDTO {
    Long id;
    String doctorUsername;
    UserDTO user;
}
