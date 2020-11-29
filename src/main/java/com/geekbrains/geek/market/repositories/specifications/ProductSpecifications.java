package com.geekbrains.geek.market.repositories.specifications;

import com.geekbrains.geek.market.entities.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<ProductEntity> priceGreaterOrEqualsThan(int minPrice) {
        return (Specification<ProductEntity>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);  // where p.price >= minPrice
    }

    public static Specification<ProductEntity> priceLesserOrEqualsThan(int maxPrice) {
        return (Specification<ProductEntity>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice); // where p.price <= maxPrice
    }

    public static Specification<ProductEntity> titleLike(String titlePart) {
        return (Specification<ProductEntity>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart)); // where p.title like %titlePart%
    }
    public static Specification<ProductEntity> categoryEquals(Long category) {
        return (Specification<ProductEntity>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("category"), category); // where p.category = category
    }
}
