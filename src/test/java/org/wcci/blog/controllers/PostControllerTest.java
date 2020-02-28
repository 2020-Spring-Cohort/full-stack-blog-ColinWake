package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class PostControllerTest {

    private MockMvc mockMvc;
    private PostController underTest;
    private PostStorage mockStorage;
    private AuthorStorage mockAuthorStorage;
    private CategoryStorage mockCategoryStorage;
    private Model mockModel;
    private Post testPost;
    private Author testAuthor;
    private Category testCategory;

    @BeforeEach
    void setUp() {
        mockModel = mock(Model.class);
        mockStorage = mock(PostStorage.class);
        mockAuthorStorage = mock(AuthorStorage.class);
        mockCategoryStorage = mock(CategoryStorage.class);
        underTest = new PostController(mockStorage, mockAuthorStorage, mockCategoryStorage);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        testAuthor = new Author("Test Tester");
        testCategory = new Category("Test Category");
        testPost = new Post("Test", "Test", testAuthor, testCategory);
        when(mockAuthorStorage.findAuthorByName("Test Tester")).thenReturn(testAuthor);
        when(mockCategoryStorage.findCategoryByName("Test Category")).thenReturn(testCategory);
        when(mockStorage.findPostById(1L)).thenReturn(testPost);
    }

    @Test
    void displayPosts() throws Exception {
        List<Post> testList = Collections.singletonList(testPost);

        when(mockStorage.getAll()).thenReturn(testList);

        mockMvc.perform(get("/posts/"))
                .andDo(print())
                .andExpect(model().attributeExists("posts"))
                .andExpect(view().name("posts"))
                .andExpect(model().attribute("posts", testList))
                .andExpect(status().isOk());
    }

    @Test
    void displayPost() {
        underTest.displayPost("1", mockModel);

        verify(mockStorage).findPostById(1L);
        verify(mockModel).addAttribute("post", testPost);
    }

    @Test
    void addPost() {
        String viewName = underTest.addPost("Test Category", "Test Tester", "Test", "Test");

        verify(mockStorage).store(new Post("Test", "Test", testAuthor, testCategory));

        assertThat(viewName).isEqualTo("redirect:");
    }
}