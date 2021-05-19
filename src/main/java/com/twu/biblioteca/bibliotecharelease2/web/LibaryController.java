package com.twu.biblioteca.bibliotecharelease2.web;

import com.twu.biblioteca.bibliotecharelease2.domain.Book;
import com.twu.biblioteca.bibliotecharelease2.services.LibaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/libary")
public class LibaryController {
    @Autowired
    private LibaryService libaryService;

    @GetMapping("/books")
    public ArrayList<Book> getAllBooks () {
        return libaryService.getAvailableBook();
    }
}