package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import se331.lab.rest.entity.Vaccine;
import se331.lab.rest.service.VaccineService;
import se331.lab.rest.util.LabMapper;

import java.util.List;

@Controller
public class VaccineController {
    @Autowired
    VaccineService vaccineService;

    @GetMapping("vaccine")
    public ResponseEntity<?> GetVaccines(
            @RequestParam(name = "page", required = false)Integer page,
            @RequestParam(name = "pageSize", required = false)Integer pageSize
    ){
        page = page == null?1:page;
        pageSize = pageSize == null?3:pageSize;
        Page<Vaccine> vaccines = vaccineService.all(page-1, pageSize);

        Integer size = vaccineService.size();
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-total-count", String.valueOf(size));

        return new ResponseEntity<>(LabMapper.INSTANCE.getVaccine(vaccines.getContent()), headers, HttpStatus.OK);
    }

    @GetMapping("vaccine/{id}")
    public ResponseEntity<?> GetVaccine(
            @PathVariable(name = "id")Long id
    ){
        return new ResponseEntity<>(LabMapper.INSTANCE.getVaccine(vaccineService.get(id)), HttpStatus.OK);
    }
}
