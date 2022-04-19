package com.hospital.service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class VaccineVendorResponse {

    @JsonProperty("vaccine_id")
    String vaccineId;
    @JsonProperty("vendor_id")
    String vendorId;
    @JsonProperty("vaccine_name")
    String vaccineName;
    private String vendorName;
    private String location;
    private String city;
    @JsonProperty("available_count")
    int availableCount;
    @JsonProperty("exp_date")
    String expiryDate;

    public String getVaccineId() {
        return vaccineId;
    }
    public void setVaccineId(String vaccineId) {
        this.vaccineId = vaccineId;
    }
    public String getVendorId() {
        return vendorId;
    }
    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
    public String getVaccineName() {
        return vaccineName;
    }
    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }
    public int getAvailableCount() {
        return availableCount;
    }
    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }
    public String getExpiryDate() {
        return expiryDate;
    }
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }
    public String getVendorName() {
        return vendorName;
    }
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

}