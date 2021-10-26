package se331.lab.rest.security.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import se331.lab.rest.entity.VaccineDTO;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
     Long id;
     String username;
     String firstname;
     String lastname;
     String address;
     Integer age;
     String img;
     String comment;
     List<AuthorityDTO> authorities = new ArrayList<>();
     List<VaccineDTO> vaccines = new ArrayList<>();
}
