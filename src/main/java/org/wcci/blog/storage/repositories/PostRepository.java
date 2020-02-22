package org.wcci.blog.storage.repositories;

import org.springframework.data.repository.CrudRepository;
import org.wcci.blog.models.Post;

import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long> {

    Optional<Post> findByTitle(String name);

}
