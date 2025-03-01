package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionRepository extends JpaRepository<Division,String> {
    Division findByDivisionName(String divisionName);

    Division findByDivisionCode(String divisionCode);
}
