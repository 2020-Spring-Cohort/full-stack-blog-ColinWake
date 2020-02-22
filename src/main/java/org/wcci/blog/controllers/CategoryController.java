package org.wcci.blog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wcci.blog.models.Category;
import org.wcci.blog.storage.CategoryStorage;

@Controller
@RequestMapping("categories")
public class CategoryController {

    CategoryStorage categoryStorage;

    public CategoryController(CategoryStorage categoryStorage) {
        this.categoryStorage = categoryStorage;
    }

    @RequestMapping
    public String displayCategories(Model model) {
        model.addAttribute("categories", categoryStorage.getAll());

        return "categories";
    }

    @GetMapping("/{name}")
    public String displayCategory(@PathVariable String name, Model model) {
        Category retrievedCategory = categoryStorage.findCategoryByName(name);

        model.addAttribute("category", retrievedCategory);

        return "category";
    }

    @PostMapping("/add-category")
    public String addCategory(@RequestParam String name) {
        categoryStorage.store(new Category(name));

        return "redirect:";
    }
}
