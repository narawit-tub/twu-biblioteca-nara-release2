package com.twu.biblioteca.bibliotecharelease2;

import com.twu.biblioteca.bibliotecharelease2.domain.Book;
import com.twu.biblioteca.bibliotecharelease2.services.LibaryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class HttpRequestTesting {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibaryService libaryService;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to libary!")));
    }

    @Test
    public void shouldReturnAListOfBooks() throws Exception{
        // given
        List<Book> bookList = Arrays.asList(
                new Book("Work life balance", "nara", "2016"),
                new Book("Japan is hard", "mormew", "2015"));
        ArrayList<Book> booksArrayList = new ArrayList<>();
        booksArrayList.addAll(bookList);
        when(libaryService.getAvailableBook()).thenReturn(booksArrayList);

        // when + then
        this.mockMvc.perform(get("/api/libary/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value("nara"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookName").value("Work life balance"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].publicationYear").value("2016"));
    }
}
