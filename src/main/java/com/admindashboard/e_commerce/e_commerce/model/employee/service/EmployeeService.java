package com.admindashboard.e_commerce.e_commerce.model.employee.service;

import com.admindashboard.e_commerce.e_commerce.authorization.Role;
import com.admindashboard.e_commerce.e_commerce.authorization.RoleRepository;
import com.admindashboard.e_commerce.e_commerce.authorization.User;
import com.admindashboard.e_commerce.e_commerce.authorization.UserRepository;
import com.admindashboard.e_commerce.e_commerce.model.employee.dto.EmployeeRequestDto;
import com.admindashboard.e_commerce.e_commerce.model.employee.dto.EmployeeResponseDto;
import com.admindashboard.e_commerce.e_commerce.model.employee.entity.Employee;
import com.admindashboard.e_commerce.e_commerce.model.employee.repository.EmployeeRepository;
import com.admindashboard.e_commerce.e_commerce.utils.UserUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EmployeeService {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private EmployeeRepository employeeRepository;


    @Autowired
    UserRepository userRepository;

    @Autowired
    UserUtils userUtils;

    @Autowired
    RoleRepository roleRepository;





    @Transactional
    public EmployeeResponseDto addEmployee(EmployeeRequestDto employeeRequestDto)
    {

        User user = new User();
        Optional<User> user1 = userRepository.findByUserName(employeeRequestDto.getUserName());


        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();

        if(user1.isEmpty()) {

            user.setUserName(employeeRequestDto.getUserName());
            user.setPassword(passwordEncoder.encode(employeeRequestDto.getPassword()));

            Set<Role> roleSet = new HashSet();
            Role role = roleRepository.findByAuthority("SUPPORT");
            roleSet.add(role);
            user.setRoles(roleSet);

            Employee employee = new Employee();
            employee.setUser(user);
            employee.setContactNumber(employeeRequestDto.getContactNumber());
            employee.setFullName(employeeRequestDto.getFullName());
            employee.setCreatedDate(new Date());
            employee.setCommunicationEmail(employeeRequestDto.getCommunicationEmail());
            employee.setContactNumber(employeeRequestDto.getContactNumber());
            employee = employeeRepository.save(employee);
            user.setEmployee(employee);
            user.setTenantId(employee.getId());
            user.setCreatedDate(new Date());
            user = userRepository.save(user);
            employee = employeeRepository.save(employee);

            employeeResponseDto.setFullName(employee.getFullName());
            employeeResponseDto.setUserName(user.getUsername());
            employeeResponseDto.setContactNumber(employee.getContactNumber());
            employeeResponseDto.setCommunicationEmail(employee.getCommunicationEmail());
            employeeResponseDto.setRole(role.getAuthority());
            System.out.println(employeeResponseDto);
            return employeeResponseDto;
        }else {
            employeeResponseDto.setMessage("user already exits");
            return  employeeResponseDto;
        }
    }
}
