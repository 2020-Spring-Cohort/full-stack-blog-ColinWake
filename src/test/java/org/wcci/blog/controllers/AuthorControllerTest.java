package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.models.Author;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.PostStorage;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class AuthorControllerTest {

    private AuthorController underTest;
    private PostStorage mockPostStorage;
    private AuthorStorage mockAuthorStorage;
    private Model mockModel;
    private Author testAuthor;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockPostStorage = mock(PostStorage.class);
        mockAuthorStorage = mock(AuthorStorage.class);
        mockModel = mock(Model.class);
        underTest = new AuthorController(mockAuthorStorage, mockPostStorage);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        testAuthor = new Author("Test");
    }

    @Test
    void displayAuthors() throws Exception {
        List<Author> testList = Collections.singletonList(testAuthor);

        when(mockAuthorStorage.getAll()).thenReturn(testList);

        mockMvc.perform(get("/authors/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("authors"))
                .andExpect(model().attributeExists("authors"))
                .andExpect(model().attribute("authors", testList));
    }

    @Test
    void displayAuthor() {
        when(mockAuthorStorage.findAuthorById(1L)).thenReturn(testAuthor);

        underTest.displayAuthor("1", mockModel);

        verify(mockAuthorStorage).findAuthorById(1L);
        verify(mockModel).addAttribute("author", testAuthor);
    }

    @Test
    void addAuthor() {
        String viewName = underTest.addAuthor("Test");

        verify(mockAuthorStorage).store(new Author("Test"));

        assertThat(viewName).isEqualTo("redirect:");
    }
}