package org.wcci.blog.storage;

import org.wcci.blog.models.Category;

import java.util.Collection;

public interface CategoryStorage {

    Collection<Category> getAll();

    void store(Category category);

    Category findCategoryByName(String name);

}
