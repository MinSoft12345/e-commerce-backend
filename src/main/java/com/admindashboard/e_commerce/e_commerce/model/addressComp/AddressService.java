package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import com.admindashboard.e_commerce.e_commerce.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private DivisionRepository divisionRepository;

    @Autowired
    private SubDistrictRepository subDistrictRepository;


    public AddressDto addAddress(AddressDto addressDto)
    {
        var address = Address.builder()
                .addressLine(addressDto.getAddressLine())
                .state(addressDto.getState())
                .postCode(addressDto.getPostCode())
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .build();

        address = addressRepository.save(address);

        return AddressDto.builder()
                .id(address.getId())
                .addressLine(address.getAddressLine())
                .state(address.getState())
                .city(address.getCity())
                .postCode(address.getPostCode())
                .country(address.getCountry())
                .build();
    }

    public AddressDto addSubDistrict(AddressDto addressDto)
    {
        District district = districtRepository.findByDistrictName(addressDto.getDistrictName());
        var subDistrict = SubDistrict.builder()
                .subDistrictName(addressDto.getSubDistrictName())
                .subDistrictCode(addressDto.getSubDistrictCode())
                .district(district)
                .build();

        subDistrict = subDistrictRepository.save(subDistrict);

        return AddressDto.builder()
                .subDistrictId(subDistrict.getId())
                .subDistrictName(subDistrict.getSubDistrictName())
                .districtName(subDistrict.getDistrict().getDistrictName())
                .divisionName(subDistrict.getDistrict().getDivision().getDivisionName())
                .build();

    }

    public AddressDto addDistrict(AddressDto addressDto)
    {
        Division division = divisionRepository.findByDivisionName(addressDto.getDivisionName());
        var district = District.builder()
                .districtName(addressDto.getDistrictName())
                .districtCode(addressDto.getDistrictCode())
                .division(division)
                .build();

        district = districtRepository.save(district);

        return AddressDto.builder()
                .districtId(district.getId())
                .districtName(district.getDistrictName())
                .divisionName(district.getDivision().getDivisionName())
                .build();

    }

    public AddressDto addDivision(AddressDto addressDto)
    {
        var division = Division.builder()
                .divisionName(addressDto.getDivisionName())
                .divisionCode(addressDto.getDivisionCode())
                .postalCode(Long.valueOf(addressDto.getPostCode()))
                .build();

        division = divisionRepository.save(division);

        return AddressDto.builder()
                .districtId(division.getId())
                .divisionName(division.getDivisionName())
                .divisionCode(division.getDivisionCode())
                .postCode(Math.toIntExact(division.getPostalCode()))
                .build();

    }

    public List<District>findDistrictList(String divisionName){
        return districtRepository.findByDivisionName(divisionName);
    }

    public List<SubDistrict>findSubDistrictList(String districtName){
        return subDistrictRepository.findByDistrictName(districtName);
    }


}
