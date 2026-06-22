package com.nitin.ecommerce.controller;

import com.nitin.ecommerce.entity.Category;
import com.nitin.ecommerce.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(
            CategoryService categoryService) {

        this.categoryService = categoryService;
    }

    @PostMapping
    public Category addCategory(
            @RequestBody Category category) {

        return categoryService.save(category);
    }

    @GetMapping
    public List<Category> getAllCategories() {

        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategoryById(
            @PathVariable Integer id) {

        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public Category updateCategory(
            @PathVariable Integer id,
            @RequestBody Category category) {

        return categoryService
                .updateCategory(id, category);
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(
            @PathVariable Integer id) {

        return categoryService.deleteCategory(id);
    }

}
