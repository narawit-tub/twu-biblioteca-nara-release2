package com.twu.biblioteca.bibliotecharelease2.services;

import com.twu.biblioteca.bibliotecharelease2.domain.UserApp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class UserService {
    private UserApp currentUser;

    private ArrayList<UserApp> libarianList;
    private ArrayList<UserApp> guestList;

    public UserService() {
        // user credential hardcode
        libarianList = new ArrayList<>();
        guestList = new ArrayList<>();
        libarianList.add(new UserApp("Narawit", "Tubtimtoe", "nara@email.com", "0912345678", "libarian"));
        guestList.add(new UserApp("Narawit", "Tubtimtoe", "nara@email.com", "0912345678", "guest"));
    }

    public UserApp login (Map<String, String> userloginPayload) {
        // hardcode
        if (userloginPayload.get("libaryNumber") != null) {
            for (UserApp libarian: libarianList) {
                if (userloginPayload.get("email").equals("nara@email.com") && userloginPayload.get("password").equals("1234") && userloginPayload.get("libaryNumber").equals("123-4567")) {
                    currentUser = libarian;
                }
            }
        } else {
            for (UserApp guest: guestList) {
                if (userloginPayload.get("email").equals("nara@email.com") && userloginPayload.get("password").equals("1234") && userloginPayload.get("libaryNumber").equals("123-4567")) {
                    currentUser = guest;
                }
            }
        }

        return currentUser;
    }

    // this function also hard-code, how to persist currentUser so I can access it from any method (checkout, returnbook)
    public UserApp getCurrentUser() {
        return currentUser;
    };
}
