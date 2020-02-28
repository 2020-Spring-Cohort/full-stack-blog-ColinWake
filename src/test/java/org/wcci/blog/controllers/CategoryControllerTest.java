package org.wcci.blog.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.wcci.blog.models.Category;
import org.wcci.blog.storage.CategoryStorage;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CategoryControllerTest {

    private MockMvc mockMvc;
    private CategoryController underTest;
    private CategoryStorage mockStorage;
    private Model mockModel;
    private Category testCategory;

    @BeforeEach
    void setUp() {
        mockModel = mock(Model.class);
        mockStorage = mock(CategoryStorage.class);
        underTest = new CategoryController(mockStorage);
        mockMvc = MockMvcBuilders.standaloneSetup(underTest).build();
        testCategory = new Category("Test");
    }

    @Test
    void displayCategories() throws Exception {

        List<Category> testList = Collections.singletonList(testCategory);

        when(mockStorage.getAll()).thenReturn(testList);

        mockMvc.perform(get("/categories/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("categories"))
                .andExpect(model().attributeExists("categories"))
                .andExpect(model().attribute("categories", testList));
    }

    @Test
    void displayCategory() {

        when(mockStorage.findCategoryByName("Test Test")).thenReturn(testCategory);

        underTest.displayCategory("Test Test", mockModel);

        verify(mockStorage).findCategoryByName("Test Test");
        verify(mockModel).addAttribute("category", testCategory);
    }

    @Test
    void addCategory() {
        String viewName = underTest.addCategory("Test");

        verify(mockStorage).store(new Category("Test"));

        assertThat(viewName).isEqualTo("redirect:");
    }

    @Test
    public void addCategoryShouldRedirect() throws Exception {
        mockMvc.perform(post("/categories/add-category").param("name", "Test"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        verify(mockStorage).store(new Category("Test"));
    }
}