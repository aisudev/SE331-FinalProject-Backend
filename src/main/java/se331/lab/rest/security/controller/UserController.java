package se331.lab.rest.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.service.UserService;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("patients")
    public ResponseEntity<?> GetPatients(
            @RequestParam(name = "page")Integer page,
            @RequestParam(name = "pageSize")Integer pageSize
    ){
        page = page == null?1:page;
        pageSize = pageSize == null?1:pageSize;
        Page<User> users = userService.getPatients(page, pageSize);
        Integer size = userService.sizeOfPatient();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-total-count", String.valueOf(size));

        return new ResponseEntity<>(users.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("doctors")
    public ResponseEntity<?> GetDoctors(
            @RequestParam(name = "page")Integer page,
            @RequestParam(name = "page")Integer pageSize
    ){
        page = page == null?1:page;
        pageSize = pageSize == null?1:pageSize;

        Page<User> users = userService.getDoctors(page, pageSize);
        Integer size = userService.sizeOfDoctor();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-total-count", String.valueOf(size));

        return new ResponseEntity<>(users.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity<?>GetUsers(
            @RequestParam(name = "page")Integer page,
            @RequestParam(name = "page")Integer pageSize
    ){
        page = page == null?1:page;
        pageSize = pageSize == null?1:pageSize;

        Page<User> users = userService.all(page, pageSize);
        Integer size = userService.size();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-total-count", String.valueOf(size));

        return new ResponseEntity<>(users.getContent(), headers, HttpStatus.OK);
    }
}
