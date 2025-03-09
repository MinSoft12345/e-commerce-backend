package com.admindashboard.e_commerce.e_commerce.dto;

import com.admindashboard.e_commerce.e_commerce.authorization.User;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticatedUser extends User implements UserDetails {


    public AuthenticatedUser(User user) {
        this.setId(user.getId());
        this.setUserName(user.getUsername());
        this.setTenantId(user.getTenantId());
        this.setPassword(user.getPassword());
        this.setEmail(user.getEmail());
    }

}
