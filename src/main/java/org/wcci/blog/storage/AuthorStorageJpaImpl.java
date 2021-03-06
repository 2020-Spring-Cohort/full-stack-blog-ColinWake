package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Author;
import org.wcci.blog.storage.repositories.AuthorRepository;

import java.util.Collection;

@Service
public class AuthorStorageJpaImpl implements AuthorStorage {

    private AuthorRepository authorRepository;

    public AuthorStorageJpaImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Collection<Author> getAll() {
        return (Collection<Author>) authorRepository.findAll();
    }

    @Override
    public void store(Author author) {
        authorRepository.save(author);
    }

    @Override
    public Author findAuthorByName(String name) {
        return authorRepository.findByName(name).get();
    }

    @Override
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).get();
    }
}
