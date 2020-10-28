package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.dto.ProductDto;
import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.ProductService;
import com.geekbrains.geek.market.utils.ProductFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping(produces = "application/json")
    public Page<ProductDto> getAllProducts(@RequestParam(defaultValue = "1", name = "p") Integer page, @RequestParam Map<String, String> params) {
        ProductFilter productFilter = new ProductFilter(params);
        if (page < 1) page = 1;
        Page<Product> productsPage = productService.findAll(productFilter.getSpec(), page - 1, 5);
        return new PageImpl<>(productsPage.getContent().stream().map(ProductDto::new)
                .collect(Collectors.toList()), productsPage.getPageable(), productsPage.getTotalElements());
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product with id=" + id + " doesn't exists"));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Product createProduct(@RequestBody Product product) {
        product.setId(null);
        return productService.SaveOrUpdate(product);
    }

    @PutMapping(consumes = "application/json", produces = "application/json")
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
