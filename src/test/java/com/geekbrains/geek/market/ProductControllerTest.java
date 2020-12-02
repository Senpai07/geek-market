package com.geekbrains.geek.market;

import com.geekbrains.geek.market.controllers.ProductController;
import com.geekbrains.geek.market.dto.PageDto;
import com.geekbrains.geek.market.entities.ProductEntity;
import com.geekbrains.geek.market.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    public void getAllProductTest() throws Exception {
        ProductEntity meat = new ProductEntity();
        meat.setId(1L);
        meat.setTitle("Мясо");
        meat.setPrice(200);

        List<ProductEntity> allProductEntity = new ArrayList<>(Arrays.asList(meat));

        given(productController.getProductById(1L)).willReturn(meat);

        mvc.perform(get("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.title", is(allProductEntity.get(0).getTitle())));
    }

    @Test
    public void getPageTest() throws Exception {
        PageDto<ProductEntity> pageDto = new PageDto<>();
        ProductEntity meat = new ProductEntity();
        meat.setId(1L);
        meat.setTitle("Мясо");
        meat.setPrice(200);

        List<ProductEntity> allProductEntity = new ArrayList<>(Arrays.asList(meat));

        pageDto.setContent(allProductEntity);
        pageDto.setTotal(1);
        pageDto.setSize(5);

        Map<String, String> params = new HashMap<>();

        given(productController.getAllProducts(1, params)).willReturn(pageDto);
        given(productService.findAll(Specification.where(null), 0, 5)).willReturn(pageDto);

        mvc.perform(get("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$").isMap())
                .andExpect(jsonPath("$.content").isArray())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].title", is(pageDto.getContent().get(0).getTitle())));
    }
}
