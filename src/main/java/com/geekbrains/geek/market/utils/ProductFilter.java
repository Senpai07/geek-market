package com.geekbrains.geek.market.utils;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.repositories.specifications.ProductSpecifications;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Data
public class ProductFilter {
    private Specification<Product> spec;
    private String filterDefinition;

    public ProductFilter(Map<String, String> params) {
        spec = Specification.where(null);

        String filterValue = params.get("title");
        if (filterValue != null && !filterValue.isBlank()) {
            spec = spec.and(ProductSpecifications.titleLike(filterValue));
        }
        filterValue = params.get("min_price");
        if (filterValue != null && !filterValue.isBlank()) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(Integer.parseInt(filterValue)));
        }
        filterValue = params.get("max_price");
        if (filterValue != null && !filterValue.isBlank()) {
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(Integer.parseInt(filterValue)));
        }
        filterValue = params.get("category");
        if (filterValue != null && !filterValue.isBlank()) {
            spec = spec.and(ProductSpecifications.categoryEquals(Long.decode(filterValue)));
        }
    }
}
