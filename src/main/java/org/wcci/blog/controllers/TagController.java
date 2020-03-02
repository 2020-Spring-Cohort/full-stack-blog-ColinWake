package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.models.Tag;
import org.wcci.blog.storage.TagStorage;

@Controller
@RequestMapping("tags")
public class TagController {

    private TagStorage tagStorage;

    public TagController(TagStorage tagStorage) {
        this.tagStorage = tagStorage;
    }

    @GetMapping
    public String displayTags(Model model) {
        model.addAttribute("tags", tagStorage.getAll());

        return "tags";
    }

    @GetMapping("/{tagId}")
    public String displayTag(@PathVariable String tagId, Model model) {
        Tag retrievedTag = tagStorage.findTagById(Long.parseLong(tagId));

        model.addAttribute("tag", retrievedTag);

        return "tag";
    }

    @PostMapping("/add-tag")
    public String addTag(@RequestParam("name") String name) {
        Tag newTag = new Tag(name);

        tagStorage.store(newTag);

        return "redirect:";
    }
}
