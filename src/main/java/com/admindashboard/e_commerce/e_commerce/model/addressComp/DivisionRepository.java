package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DivisionRepository extends JpaRepository<Division,String> {
    Division findByDivisionName(String divisionName);

    Division findByDivisionCode(String divisionCode);

    @Query("SELECT u FROM Division u " +
            "join u.country d " +
            "WHERE d.countryName = :countryName")
    List<Division> findByCountryName(@Param("countryName") String countryName);
}
