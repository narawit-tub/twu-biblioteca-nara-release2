package com.twu.biblioteca.bibliotecharelease2;

import com.twu.biblioteca.bibliotecharelease2.domain.Book;
import com.twu.biblioteca.bibliotecharelease2.domain.Movie;
import com.twu.biblioteca.bibliotecharelease2.services.LibaryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LibaryTesting {
    /*
    *
    * space for release 1 testing
    *
    * */

    @Test
    public void viewAListOfBook() {
        // Given
        ArrayList<Book> books = new ArrayList<>();
        List<Book> bookList = Arrays.asList(
                new Book("Work life balance", "nara", "2016"),
                new Book("Japan is hard", "mormew", "2015"));
        books.addAll(bookList);

        // When
        LibaryService service = new LibaryService(books);
        ArrayList availableBooks = service.getAvailableBook();

        // Then
        assertEquals(2, availableBooks.size());
        assertArrayEquals(books.toArray(), availableBooks.toArray());
    }

//    @Test
//    public void viewAListOfMovies() {
//        ArrayList<Movie> movies = new ArrayList<Movie>();
//        movies.add(new Movie("Antman", "John maven", "2015"));
//        movies.add(new Movie("Water flow", "Nora", "2015"));
//        movies.add(new Movie("Land of paradise", "Kenjiro", "2012"));
//
////        assertEquals()
    }
}
