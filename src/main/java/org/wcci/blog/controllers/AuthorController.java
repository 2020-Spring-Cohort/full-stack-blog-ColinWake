package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.models.Author;
import org.wcci.blog.storage.AuthorStorage;
import org.wcci.blog.storage.PostStorage;

@Controller
@RequestMapping("authors")
public class AuthorController {

    private AuthorStorage authorStorage;
    private PostStorage postStorage;

    public AuthorController(AuthorStorage authorStorage, PostStorage postStorage) {
        this.authorStorage = authorStorage;
        this.postStorage = postStorage;
    }

    @GetMapping
    public String displayAuthors(Model model) {
        model.addAttribute("authors", authorStorage.getAll());
        return "authors";
    }

    @GetMapping("/{authorId}")
    public String displayAuthor(@PathVariable String authorId, Model model) {
        Author retrievedAuthor = authorStorage.findAuthorById(Long.parseLong(authorId));
        model.addAttribute("author", retrievedAuthor);
        return "author";
    }

    @PostMapping("/add-author")
    public String addAuthor(@RequestParam String name) {
        authorStorage.store(new Author(name));
        return "redirect:";
    }
}
