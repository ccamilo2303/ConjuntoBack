package com.mvp.conjunto.service.impl;

import com.google.firebase.auth.FirebaseAuth;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public String getCurrentUserUid() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof User) {
                String uId = ((User) principal).getUsername();
                return FirebaseAuth.getInstance().getUser(uId).getEmail();
            }
            return null;
        }catch(Exception e){
            e.printStackTrace();
            return null;

        }
    }
}