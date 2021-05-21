package com.twu.biblioteca.bibliotecharelease2.services;

import com.twu.biblioteca.bibliotecharelease2.domain.GuestUser;
import com.twu.biblioteca.bibliotecharelease2.domain.LibarianUser;
import com.twu.biblioteca.bibliotecharelease2.domain.UserApp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

@Service
public class UserService {
    private UserApp currentUser;

    private ArrayList<LibarianUser> libarianList;
    private ArrayList<GuestUser> guestList;

    public UserService() {
        // user credential hardcode
        libarianList = new ArrayList<>();
        guestList = new ArrayList<>();
        libarianList.add(new LibarianUser("Narawit", "Tubtimtoe", "nara@email.com", "0912345678", "libarian", "1234", "123-4567"));
        guestList.add(new GuestUser("Sarisa", "Tubtimtoe", "game@email.com", "0912345678", "guest", "1234"));
    }

    public UserApp login (Map<String, String> userloginPayload) {
        // hardcode
        if (userloginPayload.get("libaryNumber") != null) {
            for (LibarianUser libarian: libarianList) {
                if (libarian.getEmail().equals(userloginPayload.get("email"))
                        && libarian.getPassword().equals(userloginPayload.get("password"))
                        && libarian.getLibaryNumber().equals(userloginPayload.get("libaryNumber"))) {
                    currentUser = libarian;
                }
            }
        } else {
            for (GuestUser guest: guestList) {
                if (guest.getEmail().equals(userloginPayload.get("email"))
                        && guest.getPassword().equals(userloginPayload.get("password"))) {
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
