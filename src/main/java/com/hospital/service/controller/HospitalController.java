package com.hospital.service.controller;

import com.hospital.service.dtos.VaccinationRequest;
import com.hospital.service.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value="/api/v1/hospital")
public class HospitalController {

    @Autowired
    HospitalService service;

    @PostMapping(value="/provide_vaccine")
    public String provideVaccine(@RequestBody VaccinationRequest vaccinationRequest){

        return "";
    }
}

