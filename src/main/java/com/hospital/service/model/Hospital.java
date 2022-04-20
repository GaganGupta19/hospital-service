package com.hospital.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hospital.service.Constants;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hospitals", schema= Constants.DB_NAME)
public class Hospital {
    @JsonProperty("hospital_id")
    @Id
    String hospitalId;
    @JsonProperty("hospital_name")
    String hospitalName;
    @JsonProperty("location")
    String location;
    @JsonProperty("city")
    String city;

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
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
