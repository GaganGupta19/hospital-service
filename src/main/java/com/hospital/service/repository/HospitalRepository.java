package com.hospital.service.repository;

import com.hospital.service.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface HospitalRepository extends JpaRepository<Hospital,String> {

    @Query(value = "select h from Hospital h")
    public List<Hospital> all();

    @Modifying
    @Query(value = "INSERT INTO hospitals (hospital_id, hospital_name, location, city) VALUES (:id, :name, :location, :city)", nativeQuery = true)
    @Transactional
    void insert(@Param("id") String hospitalId,
                    @Param("name") String name,
                    @Param("location") String location,
                    @Param("city") String city
    );


//    @Modifying
//    @Transactional
//    @Query(value="update Vaccines v set v.available_count=v.available_count-1 where v.vaccine_id =?1 and v.vendor_id = ?2")
//    public void updateVaccineStock(String vaccineId, String vendorId);
//
//    @Query(value = "select v from Vaccines v where v.vaccine_id =?1")
//    public List<Vaccines> getVaccineByVaccineId(String vaccineId);
//
//    @Query(value = "select v from Vaccines v where v.vendor_id =?1")
//    public List<Vaccines> getVaccineByVendorId(String vendorId);


}