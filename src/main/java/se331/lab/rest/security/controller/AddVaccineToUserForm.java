package se331.lab.rest.security.controller;

import lombok.Data;

@Data
public class AddVaccineToUserForm {
    Long id;
    Long vaccine_id;
}
