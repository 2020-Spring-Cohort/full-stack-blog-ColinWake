package org.wcci.blog.storage;

import org.wcci.blog.models.Author;

import java.util.Collection;

public interface AuthorStorage {

    Collection<Author> getAll();

    void store(Author author);

    Author findAuthorByName(String name);

}
