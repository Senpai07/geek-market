package com.geekbrains.geek.market;

import com.geekbrains.geek.market.entities.ProductEntity;
import com.geekbrains.geek.market.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void categoryRepositoryTest() {
        ProductEntity product = new ProductEntity();
        product.setTitle("Арбуз");
        product.setPrice(100);
        testEntityManager.persist(product);
        testEntityManager.flush();

        List<ProductEntity> productEntities = productRepository.findAll();

        Assertions.assertEquals(4, productEntities.size());
        Assertions.assertEquals("Арбуз", productEntities.get(3).getTitle());
    }

    @Test
    public void initDbTest() {
        List<ProductEntity> productEntities = productRepository.findAll();
        Assertions.assertEquals(3, productEntities.size());
    }
}
