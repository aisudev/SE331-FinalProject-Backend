package se331.lab.rest.controller;

import lombok.Data;

@Data
public class AddChargeForm {
    String doctor_username;
    Long patient_id;
}
