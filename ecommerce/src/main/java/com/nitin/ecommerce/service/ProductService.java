package com.nitin.ecommerce.service;

import com.nitin.ecommerce.entity.Category;
import com.nitin.ecommerce.entity.Product;
import com.nitin.ecommerce.repository.CategoryRepository;
import com.nitin.ecommerce.repository.ProductRepository;
import org.springframework.stereotype.Service;
import com.nitin.ecommerce.dto.ProductRequest;
import com.nitin.ecommerce.dto.ProductResponse;
import com.nitin.ecommerce.exception.ProductNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product addProduct(ProductRequest request) {

        Category category = categoryRepository.findById(
                request.getCategoryId())
                .orElseThrow(() ->
                        new RuntimeException("Category not found"));

        Product product = new Product();

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setCategory(category);
        product.setStock(request.getStock());

        return productRepository.save(product);
    }

    public Product getProductById(Integer id) {

        return productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));
    }

    public Product updateProduct(Integer id, Product updatedProduct) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setStock(updatedProduct.getStock());

        return productRepository.save(product);
    }

    public String deleteProduct(Integer id) {

        if (!productRepository.existsById(id)) {
            throw new RuntimeException("Product not found");
        }

        productRepository.deleteById(id);

        return "Product Deleted";
    }

    public List<Product> searchByName(String name) {

        return productRepository.findByName(name);
    }

    public ProductResponse getProductResponse(Integer id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found"));

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

    public List<Product> getProductsCheaperThan(double price) {

        return productRepository.findByPriceLessThan(price);
    }

    public List<Product> getProductsCostlierThan(double price) {
        return productRepository.findByPriceGreaterThan(price);
    }

    public Page<Product> getProducts(int page, int size) {

        return productRepository.findAll(
                PageRequest.of(page, size));
    }

    public List<Product> getProductsSortedByPrice() {

        return productRepository.findAll(
                Sort.by("price"));
    }

    public List<Product> getExpensiveProducts(double price) {

        return productRepository.findExpensiveProducts(price);
    }

}