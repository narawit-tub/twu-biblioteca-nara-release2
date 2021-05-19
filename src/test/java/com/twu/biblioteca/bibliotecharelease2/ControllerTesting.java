package com.twu.biblioteca.bibliotecharelease2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.twu.biblioteca.bibliotecharelease2.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTesting {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to libary!")));
    }

    @Test
    public void shouldReturnAListOfBooks() throws Exception{
        this.mockMvc.perform(get("/api/libary/books"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author").value("nara"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].bookName").value("Work life balance"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].publicationYear").value("2016"));
    }

    @Test
    public void checkoutCorrectBookShouldReturnTheBookName() throws Exception {
        Book requestedBook = new Book("Work life balance", "nara", "2016");
        this.mockMvc.perform(post("/api/libary/checkout")
                    .content(new ObjectMapper().writeValueAsString(requestedBook))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("nara"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookName").value("Work life balance"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publicationYear").value("2016"));
    }

    @Test
    public void checkoutIncorrectBookShouldReturnNotify() throws Exception {
        Book requestedBook = new Book("Work life balance (wrong name)", "nara", "2016");
        this.mockMvc.perform(post("/api/libary/checkout")
                .content(new ObjectMapper().writeValueAsString(requestedBook))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("Not found book"));
    }

    @Test
    public void returnBookCorrectly() throws Exception {
        Book book = new Book("Work life balance", "nara", "2016");
        this.mockMvc.perform(post("/api/libary/checkout")
                    .content(new ObjectMapper().writeValueAsString(book))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON));
        this.mockMvc.perform(post("/api/libary/returnbook")
                    .content(new ObjectMapper().writeValueAsString(book))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.author").value("nara"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bookName").value("Work life balance"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.publicationYear").value("2016"));
    }

    @Test
    public void returnBookIncorrectly() throws Exception {
        Book book = new Book("Work life balance", "nara", "2016");
        this.mockMvc.perform(post("/api/libary/checkout")
                .content(new ObjectMapper().writeValueAsString(book))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));
        book.setBookName("Wrong name");

        this.mockMvc.perform(post("/api/libary/returnbook")
                .content(new ObjectMapper().writeValueAsString(book))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error").value("You define a wrong name of book, please recheck"));
    }
}
