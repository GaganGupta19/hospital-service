package com.hospital.service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;


public class VaccinationRequest {

    @JsonProperty("vendor_id")
    String vendorId;

    @JsonProperty("phone")
    String phone;

    @JsonProperty("vaccine_id")
    String vaccineId;

    @JsonProperty("hospital_id")
    String hospitalId;

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }
}
