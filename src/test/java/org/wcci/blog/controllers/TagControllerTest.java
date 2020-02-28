package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.TagStorage;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

class TagControllerTest {

    private TagController underTest;
    private PostStorage mockPostStorage;
    private TagStorage mockTagStorage;
    private Model mockModel;
    private Tag testTag;
    private Post testPost;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockPostStorage = mock(PostStorage.class);
        mockTagStorage = mock(TagStorage.class);
        mockModel = mock(Model.class);
        underTest = new TagController(mockTagStorage);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        testPost = new Post("Test", "Test", new Author("Test"), new Category("Test"));
        testTag = new Tag("#Test", testPost);
    }

    @Test
    void displayTags() throws Exception {
        List<Tag> testList = Collections.singletonList(testTag);

        when(mockTagStorage.getAll()).thenReturn(testList);

        mockMvc.perform(get("/tags/"))
                .andDo(print())
                .andExpect(view().name("tags"))
                .andExpect(model().attribute("tags", testList))
                .andExpect(model().attributeExists("tags"));
    }

    @Test
    void displayTag() {
        when(mockTagStorage.findTagById(1L)).thenReturn(testTag);

        underTest.displayTag("1", mockModel);

        verify(mockTagStorage).findTagById(1L);
        verify(mockModel).addAttribute("tag", testTag);
    }
}