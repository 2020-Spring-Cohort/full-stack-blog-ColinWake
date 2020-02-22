package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Category;
import org.wcci.blog.storage.repositories.CategoryRepository;

import java.util.Collection;

@Service
public class CategoryStorageJpaImpl implements CategoryStorage {

    CategoryRepository categoryRepository;

    public CategoryStorageJpaImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    protected CategoryStorageJpaImpl() {
    }

    @Override
    public Collection<Category> getAll() {
        return (Collection<Category>) categoryRepository.findAll();
    }

    @Override
    public void store(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findByName(name).get();
    }
}
