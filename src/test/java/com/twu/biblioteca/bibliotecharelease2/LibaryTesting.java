package com.twu.biblioteca.bibliotecharelease2;

import com.twu.biblioteca.bibliotecharelease2.domain.Book;
import com.twu.biblioteca.bibliotecharelease2.domain.LibaryMedia;
import com.twu.biblioteca.bibliotecharelease2.domain.Movie;
import com.twu.biblioteca.bibliotecharelease2.services.LibaryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LibaryTesting {
    /*
    *
    * space for release 1 testing
    *
    * */
    private ArrayList<Book> books;
    private ArrayList<Movie> movies;

    @BeforeEach
    public void setup () {
        books = new ArrayList<Book>();
        books.add(new Book( "nara", "Work life balance","2016"));
        books.add(new Book("mormew", "Japan is hard", "2015"));

        movies = new ArrayList<Movie>();
        movies.add(new Movie( "John maven", "Antman", "2015", 5.5));
        movies.add(new Movie( "Nora", "Water flow","2015", 7.0));
        movies.add(new Movie( "Kenjiro", "Land of paradise", "2012", 4.0));
    }

    @Test
    public void viewAListOfBook() {
        // Given
        LibaryService service = new LibaryService(books);

        // When
        ArrayList availableBooks = service.getAvailableMedia(LibaryMedia.Media_type.Book);

        // Then
        assertEquals(2, availableBooks.size());
        assertArrayEquals(books.toArray(), availableBooks.toArray());
    }

    @Test
    public void checkoutABookCorrectly () {
        // Given
        LibaryService service = new LibaryService(books);

        // When
        LibaryMedia checkedOutBook = service.checkout("Work life balance");

        // Then
        ArrayList availableBooks = service.getAvailableMedia(LibaryMedia.Media_type.Book);
        assertEquals(1, availableBooks.size());
        assertEquals(checkedOutBook.getProductName(), "Work life balance");
        assertEquals(checkedOutBook.getMaker(), "nara");
        assertEquals(checkedOutBook.getPublicationYear(), "2016");

    }

    @Test
    public void checkoutABookIncorrectly () {
        // Given
        LibaryService service = new LibaryService(books);

        // When
        LibaryMedia checkedOutBook = service.checkout("Work life balance (wrong-name)");

        // Then
        ArrayList availableBooks = service.getAvailableMedia(LibaryMedia.Media_type.Book);
        assertEquals(2, availableBooks.size());
        assertNull(checkedOutBook);
    }

    @Test
    public void returnBookCorrectly () {
        // Given
        LibaryService service = new LibaryService(books);
        LibaryMedia checkedOutBook = service.checkout("Work life balance");

        // When
        LibaryMedia returnedBook = service.returnBook("Work life balance");

        // Then
        ArrayList availableBooks = service.getAvailableMedia(LibaryMedia.Media_type.Book);
        assertEquals(2, availableBooks.size());
        assertEquals(returnedBook.getProductName(), "Work life balance");
        assertEquals(returnedBook.getMaker(), "nara");
        assertEquals(returnedBook.getPublicationYear(), "2016");
    }

    @Test
    public void returnBookIncorrectly () {
        // Given
        LibaryService service = new LibaryService(books);
        service.checkout("Work life balance");

        // When
        LibaryMedia returnedBook = service.returnBook("Work life balance (wrong-name)");

        // Then
        ArrayList availableBooks = service.getAvailableMedia(LibaryMedia.Media_type.Book);
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
