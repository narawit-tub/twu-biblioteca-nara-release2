package com.twu.biblioteca.bibliotecharelease2.web;

import com.twu.biblioteca.bibliotecharelease2.domain.Book;
import com.twu.biblioteca.bibliotecharelease2.domain.LibaryMedia;
import com.twu.biblioteca.bibliotecharelease2.services.ErrorService;
import com.twu.biblioteca.bibliotecharelease2.services.LibaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/libary")
public class LibaryController {
    @Autowired
    private LibaryService libaryService;

    @GetMapping("/books")
    public ArrayList<LibaryMedia> getAllBooks () {
        return libaryService.getAvailableBook(LibaryMedia.Media_type.Book);
    }

    @PostMapping("/books/checkout")
    public ResponseEntity<?> checkoutBook (@RequestBody Book book) {
        LibaryMedia checkedOutBook = libaryService.checkout(book.getProductName());
        if (checkedOutBook == null) return ErrorService.getErrorMap("Not found book");
        return new ResponseEntity<LibaryMedia>(checkedOutBook, HttpStatus.OK);
    }

    @PostMapping("/books/returnbook")
    public ResponseEntity<?> returnBook (@RequestBody Book book) {
        LibaryMedia returnedBook = libaryService.returnBook(book.getProductName());
        if (returnedBook == null) return ErrorService.getErrorMap("You define a wrong name of book, please recheck");
        return new ResponseEntity<LibaryMedia>(returnedBook, HttpStatus.OK);
    }
}