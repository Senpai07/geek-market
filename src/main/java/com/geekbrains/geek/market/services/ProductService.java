package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.dto.PageDto;
import com.geekbrains.geek.market.dto.ProductDto;
import com.geekbrains.geek.market.dto.products.Product;
import com.geekbrains.geek.market.entities.ProductEntity;
import com.geekbrains.geek.market.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

//    public Page<ProductEntity> findAll(Specification<ProductEntity> spec, int page, int size) {
//        return productRepository.findAll(spec, PageRequest.of(page, size));
//    }
    public PageDto<ProductEntity> findAll(Specification<ProductEntity> spec, int page, int size) {
        Page<ProductEntity> productEntityPage = productRepository.findAll(spec, PageRequest.of(page, size));
        return new PageDto(productEntityPage.getContent().stream().map(ProductDto::new).collect(Collectors.toList()),
                productEntityPage.getTotalElements(), productEntityPage.getSize());
    }

    public Optional<ProductEntity> findById(Long id) {
        return productRepository.findById(id);
    }

    public void saveProduct(ProductEntity productEntity) {
        productRepository.save(productEntity);
    }

    public ProductEntity SaveOrUpdate(ProductEntity productEntity) {
        return productRepository.save(productEntity);
    }

    public void deleteAll() {
        productRepository.deleteAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    private static final Function<ProductEntity, Product> functionEntityToSoap = se -> {
        Product s = new Product();
        s.setId(se.getId());
        s.setTitle(se.getTitle());
        s.setPrice(se.getPrice());
        return s;
    };

    public List<Product> getAllProducts() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

}
