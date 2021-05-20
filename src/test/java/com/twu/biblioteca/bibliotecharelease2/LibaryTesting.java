package com.twu.biblioteca.bibliotecharelease2;

import com.twu.biblioteca.bibliotecharelease2.domain.Book;
import com.twu.biblioteca.bibliotecharelease2.domain.Movie;
import com.twu.biblioteca.bibliotecharelease2.services.LibaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LibaryTesting {
    /*
    *
    * space for release 1 testing
    *
    * */
    private ArrayList<Book> books;

    @BeforeEach
    public void setup () {
        books = new ArrayList<>();
        List<Book> bookList = Arrays.asList(
                new Book("Work life balance", "nara", "2016"),
                new Book("Japan is hard", "mormew", "2015"));
        books.addAll(bookList);
    }

    @Test
    public void viewAListOfBook() {
        // Given
        LibaryService service = new LibaryService(books);

        // When
        ArrayList availableBooks = service.getAvailableBook();

        // Then
        assertEquals(2, availableBooks.size());
        assertArrayEquals(books.toArray(), availableBooks.toArray());
    }

    @Test
    public void checkoutABookCorrectly () {
        // Given
        LibaryService service = new LibaryService(books);

        // When
        Book checkedOutBook = service.checkoutBook("Work life balance");

        // Then
        ArrayList availableBooks = service.getAvailableBook();
        assertEquals(1, availableBooks.size());
        assertEquals(checkedOutBook.getBookName(), "Work life balance");
        assertEquals(checkedOutBook.getAuthor(), "nara");
        assertEquals(checkedOutBook.getPublicationYear(), "2016");

    }

    @Test
    public void checkoutABookIncorrectly () {
        // Given
        LibaryService service = new LibaryService(books);

        // When
        Book checkedOutBook = service.checkoutBook("Work life balance (wrong-name)");

        // Then
        ArrayList availableBooks = service.getAvailableBook();
        assertEquals(2, availableBooks.size());
        assertNull(checkedOutBook);
    }

    @Test
    public void returnBookCorrectly () {
        // Given
        LibaryService service = new LibaryService(books);
        Book checkedOutBook = service.checkoutBook("Work life balance");

        // When
        Book returnedBook = service.returnBook("Work life balance");

        // Then
        ArrayList availableBooks = service.getAvailableBook();
        assertEquals(2, availableBooks.size());
        assertEquals(returnedBook.getBookName(), "Work life balance");
        assertEquals(returnedBook.getAuthor(), "nara");
        assertEquals(returnedBook.getPublicationYear(), "2016");
    }

    @Test
    public void returnBookIncorrectly () {
        // Given
        LibaryService service = new LibaryService(books);
        service.checkoutBook("Work life balance");

        // When
        Book returnedBook = service.returnBook("Work life balance (wrong-name)");

        // Then
        ArrayList availableBooks = service.getAvailableBook();
        assertEquals(1, availableBooks.size());
        assertNull(returnedBook);
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
