package com.twu.biblioteca.bibliotecharelease2.web;

import com.twu.biblioteca.bibliotecharelease2.domain.Book;
import com.twu.biblioteca.bibliotecharelease2.domain.UserApp;
import com.twu.biblioteca.bibliotecharelease2.services.ErrorService;
import com.twu.biblioteca.bibliotecharelease2.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody Map<String, String> userloginPayload) {
        UserApp user = userService.login(userloginPayload);
        if (user == null) return ErrorService.getErrorMap("Not found user");
        return new ResponseEntity<UserApp>(user, HttpStatus.OK);
    }
}
