package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country,String> {
    Country findByCountryName(String country);

    Country findByCountryCode(String countryCode);
}
