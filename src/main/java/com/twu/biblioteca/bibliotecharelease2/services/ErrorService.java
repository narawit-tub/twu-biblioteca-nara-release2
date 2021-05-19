package com.twu.biblioteca.bibliotecharelease2.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class ErrorService {

    public static ResponseEntity<Map<String, String>> getErrorMap(String errorMessage) {
        Map<String, String> errorMap = new HashMap<>();

        errorMap.put("error", errorMessage);
        return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
