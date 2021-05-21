package com.twu.biblioteca.bibliotecharelease2.services;

import com.twu.biblioteca.bibliotecharelease2.domain.Book;
import com.twu.biblioteca.bibliotecharelease2.domain.LibaryMedia;
import com.twu.biblioteca.bibliotecharelease2.domain.Movie;
import com.twu.biblioteca.bibliotecharelease2.domain.UserApp;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class LibaryService {

    private ArrayList<LibaryMedia> libaryMedias = new ArrayList<>();

    public LibaryService() {
        // get books from source, how can I mock this !!!!
        libaryMedias.add(new Book("Work life balance", "nara", "2016"));
        libaryMedias.add(new Book("Japan is hard", "mormew", "2015"));
    }

    public LibaryService(ArrayList<? extends LibaryMedia> media) {
        libaryMedias.addAll(media);
    }

    public ArrayList<LibaryMedia> getAvailableMedia(LibaryMedia.Media_type mediaType) {
        switch (mediaType) {
            case Book:
                return new ArrayList<>(libaryMedias.stream().filter(book -> book.getAvailable() && book.getMediaType() == LibaryMedia.Media_type.Book).collect(Collectors.toList()));
            case Movie:
                return new ArrayList<>(libaryMedias.stream().filter(movie -> movie.getAvailable() && movie.getMediaType() == LibaryMedia.Media_type.Movie).collect(Collectors.toList()));
            default:
                return null;
        }
    }


    public ArrayList<LibaryMedia> getCheckedOutMedia(LibaryMedia.Media_type mediaType) {
        switch (mediaType) {
            case Book:
                return new ArrayList<>(libaryMedias.stream().filter(book -> !book.getAvailable() && book.getMediaType() == LibaryMedia.Media_type.Book).collect(Collectors.toList()));
            case Movie:
                return new ArrayList<>(libaryMedias.stream().filter(movie -> !movie.getAvailable() && movie.getMediaType() == LibaryMedia.Media_type.Movie).collect(Collectors.toList()));
            default:
                return null;
        }
    }

    public LibaryMedia checkout(String bookName, UserApp user) {
        if (user == null) return null;
        LibaryMedia checkedOutMedia = null;

        for (LibaryMedia libaryMedia : libaryMedias) {
            if (libaryMedia.getProductName().equals(bookName)) {

                if (libaryMedia.getMediaType() == LibaryMedia.Media_type.Book && user.getRole() != "libarian") return null;
                if (libaryMedia.getMediaType() == LibaryMedia.Media_type.Movie && user.getRole() != "guest") return null;

                checkedOutMedia = libaryMedia;
                libaryMedia.setAvailable(false);
                libaryMedia.setCheckedOutUser(user.getFirstname() + " " + user.getLastname());
                break;
            }
        }

        return (checkedOutMedia != null) ? checkedOutMedia : null;
    }

    public LibaryMedia returnBook(String bookName, UserApp user) {
        if(user == null) return null;
        LibaryMedia returnedMedia = null;

        for (LibaryMedia libaryMedia : libaryMedias) {
            if (libaryMedia.getProductName().equals(bookName)) {

                if (libaryMedia.getMediaType() == LibaryMedia.Media_type.Book && user.getRole() != "libarian") return null;
                if (libaryMedia.getMediaType() == LibaryMedia.Media_type.Movie && user.getRole() != "guest") return null;

                returnedMedia = libaryMedia;
                libaryMedia.setAvailable(true);
                break;
            }
        }

        return returnedMedia;
    }
}
