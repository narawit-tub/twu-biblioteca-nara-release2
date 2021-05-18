package com.twu.biblioteca.bibliotecharelease2.web;

import com.twu.biblioteca.bibliotecharelease2.services.LibaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/libary")
public class LibaryController {
    @Autowired
    private LibaryService libaryService;
}