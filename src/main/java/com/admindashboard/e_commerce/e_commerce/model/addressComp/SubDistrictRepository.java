package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubDistrictRepository extends JpaRepository<SubDistrict,String> {

    @Query("select u from SubDistrict u " +
            "join u.district d " +
            "where d.districtName = :districtName"
    )
    List<SubDistrict> findByDistrictName(@Param("districtName") String districtName);
}
