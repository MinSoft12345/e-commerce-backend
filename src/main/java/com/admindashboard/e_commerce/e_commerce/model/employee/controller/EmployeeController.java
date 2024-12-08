package com.admindashboard.e_commerce.e_commerce.model.employee.controller;

import com.admindashboard.e_commerce.e_commerce.model.employee.dto.EmployeeRequestDto;
import com.admindashboard.e_commerce.e_commerce.model.employee.dto.EmployeeResponseDto;
import com.admindashboard.e_commerce.e_commerce.model.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employee/")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("add")
    ResponseEntity<EmployeeResponseDto> addEmployee(@RequestBody EmployeeRequestDto employeeRequestDto){
        EmployeeResponseDto employeeResponseDto = employeeService.addEmployee(employeeRequestDto);
        return ResponseEntity.ok(employeeResponseDto);
    }
}
