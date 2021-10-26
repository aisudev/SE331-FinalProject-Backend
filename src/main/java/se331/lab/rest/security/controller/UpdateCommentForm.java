package se331.lab.rest.security.controller;

import lombok.Data;

@Data
public class UpdateCommentForm {
    Long id;
    String comment;
}
