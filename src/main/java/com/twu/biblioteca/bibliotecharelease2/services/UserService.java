package com.twu.biblioteca.bibliotecharelease2.services;

import com.twu.biblioteca.bibliotecharelease2.domain.UserApp;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {
    private static UserApp currentUser;

    public static UserApp login (Map<String, String> userloginPayload) {
        // hardcode
        if (userloginPayload.get("email").equals("nara@email.com") && userloginPayload.get("password").equals("1234") && userloginPayload.get("libaryNumber").equals("123-4567")) {
            currentUser = new UserApp("Narawit", "Tubtimtoe", "nara@email.com", "0912345678", "libarian");
        }
        return currentUser;
    }

    // this function also hard-code, how to persist currentUser so I can access it from any method (checkout, returnbook)
    public UserApp getCurrentUser() {
//        return new UserApp("Narawit", "Tubtimtoe", "nara@email.com", "0912345678", "libarian");
        return currentUser;
    };
}
