package org.wcci.blog;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;
import org.wcci.blog.storage.TagStorage;

@Component
public class Populator implements CommandLineRunner {

    private CategoryStorage categoryStorage;
    private AuthorStorage authorStorage;
    private PostStorage postStorage;
    private TagStorage tagStorage;

    public Populator(CategoryStorage categoryStorage, AuthorStorage authorStorage, PostStorage postStorage, TagStorage tagStorage) {
        this.categoryStorage = categoryStorage;
        this.authorStorage = authorStorage;
        this.postStorage = postStorage;
        this.tagStorage = tagStorage;
    }

    @Override
    public void run(String... args) {
        Author author = new Author("Colin");
        authorStorage.store(author);

        Category category1 = new Category("Nice");
        Category category2 = new Category("Cool");
        categoryStorage.store(category1);
        categoryStorage.store(category2);

        Post post1 = new Post("Test Title", "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab accusantium, adipisci aliquam aperiam delectus dolore dolorum earum eum eveniet exercitationem fugit id itaque mollitia nesciunt porro quasi sequi tempore vitae.", author, category1);
        Post post2 = new Post("Test Title2", "Test Body", author, category1);
        Post post3 = new Post("Test Title3", "Test Body 2", author, category2);
        postStorage.store(post1);
        postStorage.store(post2);
        postStorage.store(post3);

        Tag tag1 = new Tag("#cool", post1);
        Tag tag2 = new Tag("#testtag", post1, post2);
        Tag tag3 = new Tag("#nice", post3);
        tagStorage.store(tag1);
        tagStorage.store(tag2);
        tagStorage.store(tag3);

    }
}
