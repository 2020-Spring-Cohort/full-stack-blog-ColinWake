package org.wcci.blog.storage;

import org.wcci.blog.models.Post;

import java.util.Collection;

public interface PostStorage {

    Collection<Post> getAll();

    void store(Post post);

    Post findPostByTitle(String title);

    Post findPostById(Long id);

}
