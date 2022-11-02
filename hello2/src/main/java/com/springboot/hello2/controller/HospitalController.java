package com.springboot.hello2.controller;

import com.springboot.hello2.dao.HospitalDao;
import com.springboot.hello2.domain.Hospital;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/hospital")
public class HospitalController {
    private final HospitalDao hospitalDao;

    public HospitalController(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hospital> get(@PathVariable int id){
        try{
            Hospital hospital = this.hospitalDao.findById(id);
            return ResponseEntity.ok()
                    .body(hospital);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
