package com.nitin.ecommerce.repository;

import com.nitin.ecommerce.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository
        extends JpaRepository<Category, Integer> {
}
