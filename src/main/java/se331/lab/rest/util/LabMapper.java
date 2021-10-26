package se331.lab.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.entity.VaccineDTO;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.entity.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(imports = Collectors.class)
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);

    UserDTO getUser(User user);
    List<UserDTO> getUser(List<User> user);

    VaccineDTO getVaccine(Vaccine vaccine);
    List<VaccineDTO> getVaccine(List<Vaccine> vaccines);
}
