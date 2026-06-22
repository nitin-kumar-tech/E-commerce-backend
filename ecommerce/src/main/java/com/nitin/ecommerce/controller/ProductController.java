package com.nitin.ecommerce.controller;

import com.nitin.ecommerce.dto.ProductRequest;
import com.nitin.ecommerce.dto.ProductResponse;
import com.nitin.ecommerce.entity.Product;
import com.nitin.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {

        return productService.getProductById(id);
    }

    @PostMapping
    public Product addProduct(@Valid @RequestBody ProductRequest request) {

        return productService.addProduct(request);
    }

    @PutMapping("/{id}")
    public Product updateProduct(
            @PathVariable int id,
            @RequestBody Product product ){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Integer id){

        return productService.deleteProduct(id);
    }

    @GetMapping("/search")
    public List<Product> searchProduct(@RequestParam String name) {
        return productService.searchByName(name);
    }

    @GetMapping("/response/{id}")
    public ProductResponse getProductResponse(
            @PathVariable Integer id) {

        return productService.getProductResponse(id);
    }

    @GetMapping("/cheap")
    public List<Product> cheapProducts(
            @RequestParam double price) {

        return productService.getProductsCheaperThan(price);
    }

    @GetMapping("/expensive")
    public List<Product> expensiveProducts(
            @RequestParam double price) {

        return productService.getProductsCostlierThan(price);
    }

    @GetMapping("/page")
    public Page<Product> getProductsPage(
            @RequestParam int page,
            @RequestParam int size) {

        return productService.getProducts(page, size);
    }

    @GetMapping("/sorted")
    public List<Product> getProductsSorted() {

        return productService.getProductsSortedByPrice();
    }

    @GetMapping("/custom-expensive")
    public List<Product> getExpensiveProducts(
            @RequestParam double price) {

        return productService.getExpensiveProducts(price);
    }
}