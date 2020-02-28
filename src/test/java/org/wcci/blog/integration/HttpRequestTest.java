package org.wcci.blog.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
public class HttpRequestTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public void CategoriesEndPointShouldBeOk() {
        ResponseEntity<String> res = testRestTemplate.getForEntity("http://localhost:" + port + "/categories", String.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void PostsEndPointShouldBeOk() {
        ResponseEntity<String> res = testRestTemplate.getForEntity("http://localhost:" + port + "/posts", String.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void AuthorsEndPointShouldBeOk() {
        ResponseEntity<String> res = testRestTemplate.getForEntity("http://localhost:" + port + "/authors", String.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void TagsEndPointShouldBeOk() {
        ResponseEntity<String> res = testRestTemplate.getForEntity("http://localhost:" + port + "/tags", String.class);
        assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
