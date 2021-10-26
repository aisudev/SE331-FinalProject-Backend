package se331.lab.rest.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se331.lab.rest.security.entity.User;
import se331.lab.rest.security.service.UserService;
import se331.lab.rest.util.LabMapper;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("patients")
    public ResponseEntity<?> GetPatients(
            @RequestParam(name = "page", required = false)Integer page,
            @RequestParam(name = "pageSize", required = false)Integer pageSize
    ){
        page = page == null?1:page;
        pageSize = pageSize == null?3:pageSize;
        Page<User> users = userService.getPatients(page-1, pageSize);
        Integer size = userService.sizeOfPatient();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-total-count", String.valueOf(size));

        return new ResponseEntity<>(LabMapper.INSTANCE.getUser(users.getContent()), headers, HttpStatus.OK);
    }

    @GetMapping("doctors")
    public ResponseEntity<?> GetDoctors(
            @RequestParam(name = "page", required = false)Integer page,
            @RequestParam(name = "pageSize", required = false)Integer pageSize
    ){
        page = page == null?1:page;
        pageSize = pageSize == null?3:pageSize;

        Page<User> users = userService.getDoctors(page-1, pageSize);
        Integer size = userService.sizeOfDoctor();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-total-count", String.valueOf(size));

        return new ResponseEntity<>(LabMapper.INSTANCE.getUser(users.getContent()), headers, HttpStatus.OK);
    }

    @GetMapping("users")
    public ResponseEntity<?>GetUsers(
            @RequestParam(name = "page", required = false)Integer page,
            @RequestParam(name = "pageSize", required = false)Integer pageSize
    ){
        page = page == null?1:page;
        pageSize = pageSize == null?3:pageSize;

        Page<User> users = userService.all(page-1, pageSize);
        Integer size = userService.size();

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-total-count", String.valueOf(size));

        return new ResponseEntity<>(LabMapper.INSTANCE.getUser(users.getContent()), headers, HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<?> GetUser(
            @PathVariable(name = "id") Long id
    ){
        return ResponseEntity.ok(LabMapper.INSTANCE.getUser(userService.get(id)));
    }

    @PutMapping("admin/addVaccineToUser")
    public  ResponseEntity<?> AddVaccineToUser(
            @RequestBody AddVaccineToUserForm form
    ){
        User user = userService.addVaccineToUser(form.id, form.vaccine_id);

        if(user == null){
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(LabMapper.INSTANCE.getUser(user));
    }

    @PutMapping("doctor/addCommentToUser")
    public ResponseEntity<?> AddCommentToUser(
            @RequestBody UpdateCommentForm form
    ){
      User user = userService.updateComment(form.id, form.comment);
      return ResponseEntity.ok(LabMapper.INSTANCE.getUser(user));
    }

    @PutMapping("user/{id}")
    public ResponseEntity<?>UpdateToUser(
            @PathVariable(name = "id")Long id,
            @RequestBody() UpdateUserForm form
            ){
                User user = userService.updateUser(id, form);
                if(user == null){
                    return ResponseEntity.badRequest().build();
                }

                return new ResponseEntity<>(LabMapper.INSTANCE.getUser(user), HttpStatus.OK);
    }

    @PutMapping("user/roleToAdmin/{id}")
    public ResponseEntity<?> RoleToAdmin(@PathVariable(name = "id")Long id){
        User user = userService.roleToAdmin(id);
        return ResponseEntity.ok(LabMapper.INSTANCE.getUser(user));
    }

    @PutMapping("user/roleToDoctor/{id}")
    public ResponseEntity<?> RoleToDoctor(@PathVariable(name = "id")Long id){
        User user = userService.roleToDoctor(id);
        return ResponseEntity.ok(LabMapper.INSTANCE.getUser(user));
    }

    @PutMapping("user/roleToPatient/{id}")
    public ResponseEntity<?> RoleToPatient(@PathVariable(name = "id")Long id){
        User user = userService.roleToPatient(id);
        return ResponseEntity.ok(LabMapper.INSTANCE.getUser(user));
    }
}
