package se331.lab.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import se331.lab.rest.entity.Charge;
import se331.lab.rest.service.ChargeService;
import se331.lab.rest.util.LabMapper;

@Controller
public class ChargeController {

    @Autowired
    ChargeService chargeService;

    @GetMapping("charge")
    public ResponseEntity<?> GetCharges(
            @RequestParam(name = "page", required = false)Integer page,
            @RequestParam(name = "pageSize", required = false)Integer pageSize,
            @RequestParam(name = "username", required = false)String username
    ){
        page = page == null?1:page;
        pageSize = pageSize == null?3:pageSize;
        Page<Charge> charges;
        Integer size;

        if(username != null){
            charges = chargeService.findByDoctorUsername(username, page-1, pageSize);
            size = chargeService.sizeByDoctorUsername(username);
        }else{
            charges = chargeService.all(page - 1, pageSize);
            size = chargeService.size();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-total-count", String.valueOf(size));

        return new ResponseEntity<>(LabMapper.INSTANCE.getCharge(charges.getContent()), headers, HttpStatus.OK);
    }

    @PostMapping("admin/charge")
    public ResponseEntity<?> AddCharge(
            @RequestBody AddChargeForm form
    ){
        Charge charge = chargeService.save(form.doctor_username, form.patient_id);
        if(charge == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(LabMapper.INSTANCE.getCharge(charge));
    }

}
