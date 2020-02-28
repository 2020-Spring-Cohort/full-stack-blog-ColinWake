package org.wcci.blog.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.storage.repositories.PostRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class PostStorageJpaImplTest {

    private PostStorage underTest;
    private PostRepository postRepository;
    private Post post;

    @BeforeEach
    void setUp() {
        postRepository = mock(PostRepository.class);
        underTest = new PostStorageJpaImpl(postRepository);
        Category category = new Category("Test");
        Author author = new Author("Tester");
        post = new Post("Test Title", "Test Body", author, category);
    }

    @Test
    public void shouldFindPostByTitle() {
        when(postRepository.findByTitle("Test Title")).thenReturn(Optional.of(post));
        Post retrieved = underTest.findPostByTitle("Test Title");
        assertThat(post).isEqualTo(retrieved);
    }

    @Test
    public void shouldFindPostById() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        Post retrieved = underTest.findPostById(1L);
        assertThat(post).isEqualTo(retrieved);
    }

    @Test
    public void shouldStorePost() {
        underTest.store(post);

        verify(postRepository).save(post);
    }
}
