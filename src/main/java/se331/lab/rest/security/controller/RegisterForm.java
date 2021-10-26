package se331.lab.rest.security.controller;

import lombok.Data;

@Data
public class RegisterForm {
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String address;
    private Integer age;
    private String img;
}
