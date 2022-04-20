package com.hospital.service.controller;

import com.hospital.service.dtos.VaccinationRequest;
import com.hospital.service.dtos.VaccinationResponse;
import com.hospital.service.model.Hospital;
import com.hospital.service.model.HospitalRecord;
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
    public VaccinationResponse provideVaccine(@RequestBody VaccinationRequest vaccinationRequest){
        return service.provideVaccineToPatient(vaccinationRequest);
    }

    @GetMapping(value="/list")
    public List<Hospital> getHospitals(){
        return service.getHospitals();
    }

    @GetMapping(value="/records")
    public List<HospitalRecord> getRecords(){
        return service.getHospitalRecords();
    }

    @PostMapping(value="/add")
    public Boolean addHospital(@RequestBody Hospital hospital){
        return service.insertHospital(hospital);
    }
}

