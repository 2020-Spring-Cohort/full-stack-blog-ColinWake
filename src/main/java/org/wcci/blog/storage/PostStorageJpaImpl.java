package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Post;
import org.wcci.blog.storage.repositories.PostRepository;

import java.util.Collection;

@Service
public class PostStorageJpaImpl implements PostStorage {

    private PostRepository postRepository;

    public PostStorageJpaImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Collection<Post> getAll() {
        return (Collection<Post>) postRepository.findAll();
    }

    @Override
    public void store(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post findPostByTitle(String title) {
        return postRepository.findByTitle(title).get();
    }

    @Override
    public Post findPostById(Long id) {
        return postRepository.findById(id).get();
    }
}
