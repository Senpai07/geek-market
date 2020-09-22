package com.geekbrains.geek.market.repositories;

import com.geekbrains.geek.market.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceIsGreaterThanEqual(int price);

    List<Product> findAllByPriceIsLessThanEqual(int price);

    List<Product> findAllByPriceIsGreaterThanAndPriceIsLessThan(int price_min, int price_max);
}
