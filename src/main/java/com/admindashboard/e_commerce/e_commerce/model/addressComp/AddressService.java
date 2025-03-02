package com.admindashboard.e_commerce.e_commerce.model.addressComp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public AddressDto addDistrict(AddressDto addressDto) {
        Division division = divisionRepository.findByDivisionName(addressDto.getDivisionName());
        if (division == null) {
            throw new RuntimeException("Division not found");
        }

        District district = District.builder()
                .districtName(addressDto.getDistrictName())
                .districtCode(addressDto.getDistrictCode())
                .division(division)
                .build();

        district = districtRepository.save(district);

        return AddressDto.builder()
                .districtId(district.getId())
                .districtName(district.getDistrictName())
                .divisionName(division.getDivisionName())
                .build();
    }

    public AddressDto addDivision(AddressDto addressDto) {
        Division division = divisionRepository.findByDivisionCode(addressDto.getDivisionName());

        if (division == null) {
            division = Division.builder()
                    .divisionName(addressDto.getDivisionName())
                    .divisionCode(addressDto.getDivisionCode())
                    .postalCode(Long.valueOf(addressDto.getPostCode()))
                    .build();

            division = divisionRepository.save(division);
        }

        return AddressDto.builder()
                .divisionId(division.getId())
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
