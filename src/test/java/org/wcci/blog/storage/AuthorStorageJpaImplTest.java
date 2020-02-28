package org.wcci.blog.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Author;
import org.wcci.blog.storage.repositories.AuthorRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class AuthorStorageJpaImplTest {

    private AuthorStorage underTest;
    private AuthorRepository authorRepository;
    private Author author;

    @BeforeEach
    void setUp() {
        authorRepository = mock(AuthorRepository.class);
        underTest = new AuthorStorageJpaImpl(authorRepository);
        author = new Author("Tester");
    }

    @Test
    public void shouldFindAuthorByName() {
        when(authorRepository.findByName("Tester")).thenReturn(Optional.of(author));

        Author retrieved = underTest.findAuthorByName("Tester");

        assertThat(author).isEqualTo(retrieved);
    }

    @Test
    public void shouldFindAuthorById() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Author retrieved = underTest.findAuthorById(1L);

        assertThat(author).isEqualTo(retrieved);
    }

    @Test
    public void shouldStoreAuthor() {
        underTest.store(author);

        verify(authorRepository).save(author);
    }
}
