package org.wcci.blog.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.TagStorage;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SpringWebAppTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthorStorage authorStorage;
    @MockBean
    private CategoryStorage categoryStorage;
    @MockBean
    private PostStorage postStorage;
    @MockBean
    private TagStorage tagStorage;

    @Test
    public void shouldReceiveOkFromAuthorsEndPoint() throws Exception {
        mockMvc.perform(get("/authors/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReceiveOkFromCategoriesEndPoint() throws Exception {
        mockMvc.perform(get("/categories/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReceiveOkFromPostsEndPoint() throws Exception {
        mockMvc.perform(get("/posts/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReceiveOkFromTagsEndPoint() throws Exception {
        mockMvc.perform(get("/tags/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
