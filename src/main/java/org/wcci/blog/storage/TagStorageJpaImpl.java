package org.wcci.blog.storage;

import org.springframework.stereotype.Service;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.repositories.TagRepository;

import java.util.Collection;

@Service
public class TagStorageJpaImpl implements TagStorage {

    TagRepository tagRepository;

    public TagStorageJpaImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    protected TagStorageJpaImpl() {
    }

    @Override
    public Collection<Tag> getAll() {
        return (Collection<Tag>) tagRepository.findAll();
    }

    @Override
    public void store(Tag tag) {
        tagRepository.save(tag);
    }
}
