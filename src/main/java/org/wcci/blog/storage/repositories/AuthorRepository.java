package org.wcci.blog.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.models.Author;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    Optional<Author> findByName(String name);

}
