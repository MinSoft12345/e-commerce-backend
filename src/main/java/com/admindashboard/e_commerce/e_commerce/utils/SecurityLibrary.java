package com.admindashboard.e_commerce.e_commerce.utils;

import com.admindashboard.e_commerce.e_commerce.authorization.User;
import com.admindashboard.e_commerce.e_commerce.dto.AuthenticatedUser;
import com.admindashboard.e_commerce.e_commerce.exceptions.SecurityRuntimeException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityLibrary {
    public static User getLoggedInUser() throws SecurityRuntimeException {
        if (SecurityContextHolder.getContext() == null) {
            throw new SecurityRuntimeException();
        }
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        if (currentUser == null)
            throw new SecurityRuntimeException();

        if (currentUser instanceof UsernamePasswordAuthenticationToken) {
            User loggedInUser = new User();

            User userPrincipal = (User) currentUser.getPrincipal();
            AuthenticatedUser user = new AuthenticatedUser(userPrincipal);
            if (user != null) {
                loggedInUser.setId(user.getId());
                loggedInUser.setPassword(user.getPassword());
                loggedInUser.setPhoneNo(user.getPhoneNo());
                loggedInUser.setUserName(user.getUsername());
                loggedInUser.setTenantId(user.getTenantId());
                loggedInUser.setEmail(user.getEmail());
                loggedInUser.setIsDeleted(false);
                return loggedInUser;
            } else
                throw new SecurityRuntimeException();
        } else
            throw new SecurityRuntimeException();
    }
}
