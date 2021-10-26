package se331.lab.rest.security.controller;

import lombok.Data;

@Data
public class UpdateUserForm {
    String firstname;
    String lastname;
    String address;
    Integer age;
}
