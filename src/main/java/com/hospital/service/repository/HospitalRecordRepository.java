package com.hospital.service.repository;

import com.hospital.service.model.Hospital;
import com.hospital.service.model.HospitalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface HospitalRecordRepository extends JpaRepository<HospitalRecord,String> {

    @Query(value = "select h from HospitalRecord h order by h.createdAt desc")
    public List<HospitalRecord> all();

    @Modifying
    @Query(value = "INSERT INTO `hospital_records` (`patient_phone`, `vaccine_name`, `vendor_id`, `hospital_id`, `created_at`, `updated_at`) VALUES (:phone, :vcname, :vid, :hid, :date, :date)", nativeQuery = true)
    @Transactional
    void insert(@Param("phone") String phone,
                @Param("vcname") String vaccineName,
                @Param("vid") String vid,
                @Param("hid") String hid,
                @Param("date")Date date);
}
