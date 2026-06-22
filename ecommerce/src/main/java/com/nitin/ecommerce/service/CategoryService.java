package com.nitin.ecommerce.service;

import com.nitin.ecommerce.entity.Category;
import com.nitin.ecommerce.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Integer id) {

        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));
    }

    public Category updateCategory(
            Integer id,
            Category updatedCategory) {

        Category category =
                categoryRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Category not found"));

        category.setName(
                updatedCategory.getName());

        return categoryRepository.save(category);
    }

    public String deleteCategory(Integer id) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        if (!category.getProducts().isEmpty()) {
            throw new RuntimeException(
                    "Cannot delete category because products exist in it"
            );
        }

        categoryRepository.delete(category);

        return "Category Deleted";
    }
}
