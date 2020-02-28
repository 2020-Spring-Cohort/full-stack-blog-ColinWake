package org.wcci.blog.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.repositories.AuthorRepository;
import org.wcci.blog.storage.repositories.CategoryRepository;
import org.wcci.blog.storage.repositories.PostRepository;
import org.wcci.blog.storage.repositories.TagRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DirtiesContext
public class JpaWiringTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void categoryShouldHaveAListOfPosts() {
        Category underTest = new Category("Test");
        Author testAuthor = new Author("Test");
        Post testPost = new Post("Test", "Test", testAuthor, underTest);

        categoryRepository.save(underTest);
        authorRepository.save(testAuthor);
        postRepository.save(testPost);

        testEntityManager.flush();
        testEntityManager.clear();

        Optional<Category> optional = categoryRepository.findByName("Test");
        Category retrieved = optional.get();

        assertThat(retrieved.getPosts()).contains(testPost);
    }

    @Test
    public void tagCanHaveMultiplePosts() {
        Tag underTest = new Tag("Test");

        Author testAuthor = new Author("Tester");

        Category testCategory = new Category("Test");

        Post testPost1 = new Post("Test1", "Test1", testAuthor, testCategory, underTest);
        Post testPost2 = new Post("Test2", "Test2", testAuthor, testCategory, underTest);

        authorRepository.save(testAuthor);

        categoryRepository.save(testCategory);

        tagRepository.save(underTest);

        postRepository.save(testPost1);
        postRepository.save(testPost2);

        underTest.getPosts().add(testPost1);
        underTest.getPosts().add(testPost2);

        testEntityManager.flush();
        testEntityManager.clear();

        Optional<Tag> optionalTag = tagRepository.findByName("Test");
        Optional<Post> optionalPost1 = postRepository.findByTitle("Test1");
        Optional<Post> optionalPost2 = postRepository.findByTitle("Test2");

        Post post1 = optionalPost1.get();
        Post post2 = optionalPost2.get();

        Tag retrieved = optionalTag.get();

        assertThat(retrieved.getPosts()).contains(testPost1, testPost2);
        assertThat(post1.getTags()).contains(underTest);
        assertThat(post2.getTags()).contains(underTest);
    }

}
