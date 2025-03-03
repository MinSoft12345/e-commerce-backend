package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictRepository extends JpaRepository<District,String> {
    District findByDistrictName(String districtName);

    District findByDistrictCode(String districtCode);

    @Query("SELECT u FROM District u " +
            "join u.division d " +
            "WHERE d.divisionName = :divisionName")
    List<District> findByDivisionName(@Param("divisionName") String divisionName);
}
