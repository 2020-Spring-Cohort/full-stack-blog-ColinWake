package org.wcci.blog.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Lob
    private String body;
    @ManyToOne
    private Author author;
    private String date;
    @ManyToOne
    private Category category;
    @ManyToMany(mappedBy = "posts")
    private Collection<Tag> tags;

    protected Post() {
    }

    public Post(String title, String body, Author author, Category category, Tag... tags) {
        this.title = title;
        this.body = body;
        this.author = author;
        // Ex. February 20 2020 10:14 PM
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' h:mm a"));
        this.category = category;
        this.tags = new ArrayList<>(Arrays.asList(tags));
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Author getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public Category getCategory() {
        return category;
    }

    public Collection<Tag> getTags() {
        return tags;
    }

    public void addTagToPost(Tag tag) {
        tags.add(tag);
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (!Objects.equals(title, post.title)) return false;
        if (!Objects.equals(body, post.body)) return false;
        if (!Objects.equals(author, post.author)) return false;
        if (!Objects.equals(date, post.date)) return false;
        return Objects.equals(category, post.category);
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
