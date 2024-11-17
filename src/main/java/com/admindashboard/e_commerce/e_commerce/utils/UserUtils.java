package com.admindashboard.e_commerce.e_commerce.utils;


import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserUtils {
    public String generateUniqueUserName() {
        return "ec-" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 4);
    }
}
