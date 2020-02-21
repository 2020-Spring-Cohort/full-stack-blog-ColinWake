package org.wcci.blog.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;

@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String body;
    @ManyToOne
    private Author author;
    private String date;
    @ManyToOne
    private Category category;
    @ManyToMany(mappedBy = "posts")
    private Collection<Tag> tags;

    public Post() {
    }

    public Post(String title, String body, Author author, Category category, Tag... tags) {
        this.title = title;
        this.body = body;
        this.author = author;
        // Ex. February 20 2020 10:14 PM
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("MMMM dd yyyy hh:mm a"));
        this.category = category;
        this.tags = Arrays.asList(tags);
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
}
