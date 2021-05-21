package com.twu.biblioteca.bibliotecharelease2;

import com.twu.biblioteca.bibliotecharelease2.domain.Book;
import com.twu.biblioteca.bibliotecharelease2.domain.LibaryMedia;
import com.twu.biblioteca.bibliotecharelease2.domain.Movie;
import com.twu.biblioteca.bibliotecharelease2.domain.UserApp;
import com.twu.biblioteca.bibliotecharelease2.services.LibaryService;
import com.twu.biblioteca.bibliotecharelease2.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private Map<String, String> libarianloginPayload;
    private Map<String , String> guestloginPayload;

    @BeforeEach
    public void setup () {
        books = new ArrayList<Book>();
        books.add(new Book( "nara", "Work life balance","2016"));
        books.add(new Book("mormew", "Japan is hard", "2015"));

        movies = new ArrayList<Movie>();
        movies.add(new Movie( "John maven", "Antman", "2015", 5.5));
        movies.add(new Movie( "Nora", "Water flow","2015", 7.0));
        movies.add(new Movie( "Kenjiro", "Land of paradise", "2012", 4.0));

        libarianloginPayload = new HashMap<>();
        libarianloginPayload.put("email", "nara@email.com");
        libarianloginPayload.put("password", "1234");
        libarianloginPayload.put("libaryNumber", "123-4567");

        guestloginPayload = new HashMap<>();
        guestloginPayload.put("email", "game@email.com");
        guestloginPayload.put("password", "1234");
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
        LibaryService libService = new LibaryService(books);
        UserService userService = new UserService();
        UserApp user = userService.login(libarianloginPayload);

        // When
        LibaryMedia checkedOutBook = libService.checkout("Work life balance", user);

        // Then
        ArrayList availableBooks = libService.getAvailableMedia(LibaryMedia.Media_type.Book);
        assertEquals(1, availableBooks.size());
        assertEquals(checkedOutBook.getProductName(), "Work life balance");
        assertEquals(checkedOutBook.getMaker(), "nara");
        assertEquals(checkedOutBook.getPublicationYear(), "2016");

    }

    @Test
    public void checkoutABookIncorrectly () {
        // Given
        LibaryService libService = new LibaryService(books);
        UserService userService = new UserService();

        // When
        UserApp user = userService.login(libarianloginPayload);
        LibaryMedia checkedOutBook = libService.checkout("Work life balance (wrong-name)", user);

        // Then
        ArrayList availableBooks = libService.getAvailableMedia(LibaryMedia.Media_type.Book);
        assertEquals(2, availableBooks.size());
        assertNull(checkedOutBook);
    }

    @Test
    public void checkoutABookWithoutLogin () {
        // Given
        LibaryService libService = new LibaryService(books);

        // When
        LibaryMedia checkedOutBook = libService.checkout("Work life balance", null);

        // Then
        ArrayList availableBooks = libService.getAvailableMedia(LibaryMedia.Media_type.Book);
        assertEquals(2, availableBooks.size());
        assertNull(checkedOutBook);
    }

    @Test
    public void checkoutABookButLoginWithGuest () {
        // Given
        LibaryService libService = new LibaryService(books);
        UserService userService = new UserService();


        // When
        UserApp user = userService.login(guestloginPayload);
        LibaryMedia checkedOutBook = libService.checkout("Work life balance", user);

        // Then
        ArrayList availableBooks = libService.getAvailableMedia(LibaryMedia.Media_type.Book);
        assertEquals(2, availableBooks.size());
        assertNull(checkedOutBook);
    }

    @Test
    public void returnBookCorrectly () {
        // Given
        LibaryService service = new LibaryService(books);
        UserService userService = new UserService();
        UserApp user = userService.login(libarianloginPayload);
        service.checkout("Work life balance", user);

        // When
        LibaryMedia returnedBook = service.returnBook("Work life balance", user);

        // Then
        ArrayList availableBooks = service.getAvailableMedia(LibaryMedia.Media_type.Book);
        assertEquals(2, availableBooks.size());
        assertEquals(returnedBook.getProductName(), "Work life balance");
        assertEquals(returnedBook.getMaker(), "nara");
        assertEquals(returnedBook.getPublicationYear(), "2016");
    }

    @Test
    public void returnBookWithoutLogin () {
        // Given
        LibaryService service = new LibaryService(books);
        UserService userService = new UserService();
        UserApp user = userService.login(libarianloginPayload);
        service.checkout("Work life balance", user);

        // When
        LibaryMedia returnedBook = service.returnBook("Work life balance", null);

        // Then
        ArrayList availableBooks = service.getAvailableMedia(LibaryMedia.Media_type.Book);
        assertEquals(1, availableBooks.size());
        assertNull(returnedBook);
    }

    @Test
    public void returnBookButLoginWithGuest () {
        // Given
        LibaryService service = new LibaryService(books);
        UserService userService = new UserService();
        UserApp user = userService.login(libarianloginPayload);
        service.checkout("Work life balance", user);

        // When
        user = userService.login(guestloginPayload);
        LibaryMedia returnedBook = service.returnBook("Work life balance", user);

        // Then
        ArrayList availableBooks = service.getAvailableMedia(LibaryMedia.Media_type.Book);
        assertEquals(1, availableBooks.size());
        assertNull(returnedBook);
    }

    @Test
    public void returnBookIncorrectly () {
        // Given
        LibaryService libService = new LibaryService(books);
        UserService userService = new UserService();
        UserApp user = userService.login(libarianloginPayload);
        libService.checkout("Work life balance", user);

        // When
        LibaryMedia returnedBook = libService.returnBook("Work life balance (wrong-name)", user);

        // Then
        ArrayList availableBooks = libService.getAvailableMedia(LibaryMedia.Media_type.Book);
        assertEquals(1, availableBooks.size());
        assertNull(returnedBook);
    }

    @Test
    public void viewAListOfMovies() {
        // Given
        LibaryService service = new LibaryService(movies);

        // When
        ArrayList availableMovies = service.getAvailableMedia(LibaryMedia.Media_type.Movie);

        // Then
        assertEquals(3, availableMovies.size());
        assertArrayEquals(movies.toArray(), availableMovies.toArray());
    }

    @Test
    public void viewCheckedOutBook() {
        // Given
        LibaryService libService = new LibaryService(books);
        UserService userService = new UserService();
        UserApp user = userService.login(libarianloginPayload);
        LibaryMedia checkedOutBook = libService.checkout("Work life balance", user);

        // When
        ArrayList checkedOutBooks = libService.getCheckedOutMedia(LibaryMedia.Media_type.Book);
        ArrayList availableBooks = libService.getAvailableMedia(LibaryMedia.Media_type.Book);

        // Then
        assertEquals(1, checkedOutBooks.size());
        assertEquals(1, availableBooks.size());
        assertEquals(checkedOutBook.getProductName(), "Work life balance");
        assertEquals(checkedOutBook.getMaker(), "nara");
        assertEquals(checkedOutBook.getPublicationYear(), "2016");
        assertEquals(checkedOutBook.getCheckedOutUser(), "Narawit Tubtimtoe");
    }

    @Test
    public void viewCheckedOutBookWithoutLogin() {
        // Given
        LibaryService libService = new LibaryService(books);
        libService.checkout("Work life balance", null);

        // When
        ArrayList checkedOutBooks = libService.getCheckedOutMedia(LibaryMedia.Media_type.Book);
        ArrayList availableBooks = libService.getAvailableMedia(LibaryMedia.Media_type.Book);

        // Then
        // Then
        assertEquals(0, checkedOutBooks.size());
        assertEquals(2, availableBooks.size());
    }

    @Test
    public void loginToViewUserInfo () {
        // Given
        UserService userService = new UserService();

        // WHen
        UserApp user = userService.login(libarianloginPayload);

        // Then
        assertEquals("Narawit Tubtimtoe, email: nara@email.com, phone: 0912345678", user.toString());
    }
}
