package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.ProductService;
import com.geekbrains.geek.market.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class RestProductController {
    private ProductService productService;

    @GetMapping
    public Page<Product> getAllProducts(@RequestParam(defaultValue = "1", name = "p") Integer page, @RequestParam Map<String, String> params) {
        ProductFilter productFilter = new ProductFilter(params);
        if (page < 1) page = 1;
        return productService.findAll(productFilter.getSpec(), page - 1, 5);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with id=" + id + " doesn't exists"));
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        product.setId(null);
        return productService.SaveOrUpdate(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.SaveOrUpdate(product);
    }

    @DeleteMapping
    public void delProduct() {
        productService.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
