package com.admindashboard.e_commerce.e_commerce.updatescript;

import com.admindashboard.e_commerce.e_commerce.authorization.Role;
import com.admindashboard.e_commerce.e_commerce.authorization.RoleRepository;
import com.admindashboard.e_commerce.e_commerce.authorization.User;
import com.admindashboard.e_commerce.e_commerce.authorization.UserRepository;
import com.admindashboard.e_commerce.e_commerce.entity.Employee;
import com.admindashboard.e_commerce.e_commerce.repository.EmployeeRepository;
import com.admindashboard.e_commerce.e_commerce.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class InitialUpdateScript {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserUtils userUtils;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    // This method is strictly designed to add user in initial step
    @Transactional
    public void addInitialAdminUser() {
        User user = new User();

        Optional<User> user1 = userRepository.findByUserName("SUPPORT");

        if (user1.isEmpty()) {
            user.setUserName("SUPPORT");
            user.setPassword(passwordEncoder.encode("ADMIN1234"));

            Set<Role> roleSet = new HashSet();
            Role role = roleRepository.findByAuthority("SUPPORT");
            roleSet.add(role);
            user.setRoles(roleSet);

            Employee employee = new Employee();
            employee.setUser(user);
            employee.setContactNumber("+8801717300847");
            employee.setFullName("ADMIN SUPPORT");
            employee.setCreatedDate(new Date());
            employee.setCommunicationEmail("maeenuddinmollah@gmail.com");
            employee = employeeRepository.save(employee);
            user.setEmployee(employee);
            user.setTenantId(employee.getId());
            user.setCreatedDate(new Date());

            userRepository.save(user);
        }
    }


//    public void addRole() {
//        Role role = new Role();
//        role.setAuthority("SUPPORT");
//        role.setDescription("test data");
//        role.setCreationDateTime(new Date());
//        roleRepository.save(role);
//    }
}
