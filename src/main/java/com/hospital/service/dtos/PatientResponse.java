package com.hospital.service.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/*
*
*  "patient_id": 121212,
    "phone": 121212,
    "name": "testuser13",
    "vaccine_data": [
        {
            "vaccine_name": "covishield",
            "taken_date": "2022-03-10T00:00:00.000+00:00"
        },
*
* */
public class PatientResponse {
    @JsonProperty("patient_id")
    String patientId;

    @JsonProperty("phone")
    String phone;

    @JsonProperty("name")
    String name;

    @JsonProperty("vaccine_data")
    List<VaccinationData> vaccinationData;

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<VaccinationData> getVaccinationData() {
        return vaccinationData;
    }

    public void setVaccinationData(List<VaccinationData> vaccinationData) {
        this.vaccinationData = vaccinationData;
    }

    public static class VaccinationData {
       @JsonProperty("vaccine_name")
       String vaccineName;

       @JsonProperty("taken_date")
        Date takenDate;

        public String getVaccineName() {
            return vaccineName;
        }

        public void setVaccineName(String vaccineName) {
            this.vaccineName = vaccineName;
        }

        public Date getTakenDate() {
            return takenDate;
        }

        public void setTakenDate(Date takenDate) {
            this.takenDate = takenDate;
        }
    }
}
