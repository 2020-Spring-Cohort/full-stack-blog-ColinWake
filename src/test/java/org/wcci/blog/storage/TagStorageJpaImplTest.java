package org.wcci.blog.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.repositories.TagRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TagStorageJpaImplTest {

    private TagStorage underTest;
    private TagRepository tagRepository;
    private Tag tag;

    @BeforeEach
    void setUp() {
        tagRepository = mock(TagRepository.class);
        underTest = new TagStorageJpaImpl(tagRepository);
        tag = new Tag("#Test");
    }

    @Test
    public void shouldFindTagByName() {
        when(tagRepository.findByName("#Test")).thenReturn(Optional.of(tag));
        Tag retrieved = underTest.findTagByName("#Test");
        assertThat(tag).isEqualTo(retrieved);
    }

    @Test
    public void shouldFindTagById() {
        when(tagRepository.findById(1L)).thenReturn(Optional.of(tag));
        Tag retrieved = underTest.findTagById(1L);
        assertThat(tag).isEqualTo(retrieved);
    }

    @Test
    public void shouldStoreTag() {
        underTest.store(tag);
        verify(tagRepository).save(tag);
    }
}
