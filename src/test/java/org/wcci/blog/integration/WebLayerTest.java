package org.wcci.blog.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.TagStorage;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthorStorage authorStorage;
    @MockBean
    private PostStorage postStorage;
    @MockBean
    private CategoryStorage categoryStorage;
    @MockBean
    private TagStorage tagStorage;

    @Test
    public void postsShouldBeOKAndReturnThePostsViewWithPostsModelAttribute() throws Exception {
        mockMvc.perform(get("/posts/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("posts"))
                .andExpect(model().attributeExists("posts"));
    }

    @Test
    public void categoriesShouldBeOKAndReturnTheCategoryViewWithCategoriesModelAttribute() throws Exception {
        mockMvc.perform(get("/categories/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("categories"))
                .andExpect(model().attributeExists("categories"));
    }

    @Test
    public void tagsShouldBeOKAndReturnTheTagsViewWithTagsModelAttribute() throws Exception {
        mockMvc.perform(get("/tags/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("tags"))
                .andExpect(model().attributeExists("tags"));
    }

    @Test
    public void authorsShouldBeOKAndReturnTheAuthorsViewWithAuthorsModelAttribute() throws Exception {
        mockMvc.perform(get("/authors/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("authors"))
                .andExpect(model().attributeExists("authors"));
    }

}
