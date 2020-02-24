package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.models.Author;
import org.wcci.blog.models.Category;
import org.wcci.blog.models.Post;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.CategoryStorage;
import org.wcci.blog.storage.PostStorage;

@Controller
@RequestMapping("/posts")
public class PostController {

    PostStorage postStorage;
    AuthorStorage authorStorage;
    CategoryStorage categoryStorage;

    public PostController(PostStorage postStorage, AuthorStorage authorStorage, CategoryStorage categoryStorage) {
        this.postStorage = postStorage;
        this.authorStorage = authorStorage;
        this.categoryStorage = categoryStorage;
    }

    @GetMapping
    public String displayPosts(Model model) {
        model.addAttribute("posts", postStorage.getAll());
        model.addAttribute("categories", categoryStorage.getAll());
        model.addAttribute("authors", authorStorage.getAll());
        return "posts";
    }

    @GetMapping("/{postId}")
    public String displayPost(@PathVariable String postId, Model model) {
        Post retrievedPost = postStorage.findPostById(Long.parseLong(postId));

        model.addAttribute("post", retrievedPost);

        return "post";
    }

    @PostMapping("/add-post")
    public String addPost(@RequestParam String category, @RequestParam String author, @RequestParam String title, @RequestParam String body) {
        Author retrievedAuthor = authorStorage.findAuthorByName(author);
        Category retrievedCategory = categoryStorage.findCategoryByName(category);
        postStorage.store(new Post(title, body, retrievedAuthor, retrievedCategory));
        return "redirect:";
    }
}
