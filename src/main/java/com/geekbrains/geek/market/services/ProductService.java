package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Page<Product> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public List<Product> findByPriceIsGreaterThanEqual(int price) {
        return productRepository.findAllByPriceIsGreaterThanEqual(price);
    }
    public List<Product> findByPriceIsLessThanEqual(int price) {
        return productRepository.findAllByPriceIsLessThanEqual(price);
    }
    public List<Product> findByPriceIsGreaterThanAndPriceIsLessThan(int price_min, int price_max) {
        return productRepository.findAllByPriceIsGreaterThanAndPriceIsLessThan(price_min, price_max);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

}
