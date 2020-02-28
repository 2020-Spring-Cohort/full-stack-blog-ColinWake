package org.wcci.blog.storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.wcci.blog.models.Category;
import org.wcci.blog.storage.repositories.CategoryRepository;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class CategoryStorageJpaImplTest {

    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository = mock(CategoryRepository.class);
    }

    @Test
    public void shouldStoreCategory() {
        CategoryStorage storage = new CategoryStorageJpaImpl(categoryRepository);
        Category testCategory = new Category("helpful");
        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(testCategory));
        storage.store(testCategory);
        verify(categoryRepository).save(testCategory);
        assertThat(storage.getAll()).contains(testCategory);
    }

    @Test
    public void shouldRetrieveCategoriesByName() {
        Category testCategory1 = new Category("helpful");
        Category testCategory2 = new Category("bad");
        CategoryStorage underTest = new CategoryStorageJpaImpl(categoryRepository);
        underTest.store(testCategory1);
        underTest.store(testCategory2);
        Optional<Category> testCategory1Optional = Optional.of(testCategory1);
        when(categoryRepository.findByName("helpful")).thenReturn(testCategory1Optional);

        Optional<Category> testCategory2Optional = Optional.of(testCategory2);
        when(categoryRepository.findByName("bad")).thenReturn(testCategory2Optional);

        Category retrievedCategory1 = underTest.findCategoryByName("helpful");
        Category retrievedCategory2 = underTest.findCategoryByName("bad");
        assertThat(retrievedCategory1).isEqualTo(testCategory1);
        assertThat(retrievedCategory2).isEqualTo(testCategory2);
    }

}
