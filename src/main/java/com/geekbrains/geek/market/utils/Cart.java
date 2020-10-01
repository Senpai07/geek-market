package com.geekbrains.geek.market.utils;

import com.geekbrains.geek.market.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.loader.WebappClassLoader;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@NoArgsConstructor
@Data
public class Cart {
    private List<Product> container;

    @PostConstruct
    public void init() {
        container = new ArrayList<>();
    }

    public void add(Product product) {
        container.add(product);
    }

}
