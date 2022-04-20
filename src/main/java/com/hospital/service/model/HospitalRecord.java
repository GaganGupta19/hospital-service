package com.hospital.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hospital.service.Constants;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="hospital_records", schema= Constants.DB_NAME)
public class HospitalRecord {

    @JsonProperty("id")
    @Id
    int id;
    @JsonProperty("patient_phone")
    String patientPhone;
    @JsonProperty("vaccine_name")
    String vaccineName;
    @JsonProperty("vendor_id")
    String vendorId;
    @JsonProperty("hospital_id")
    String hospitalId;
    @JsonProperty("created_at")
    Date createdAt;
    @JsonProperty("updated_at")
    Date updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
