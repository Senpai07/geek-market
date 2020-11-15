package com.geekbrains.geek.market.utils;

import com.geekbrains.geek.market.entities.OrderItem;
import com.geekbrains.geek.market.entities.ProductEntity;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.ProductService;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class Cart {
    private final ProductService productService;
    private List<OrderItem> items;
    private int price;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void addOrIncrement(Long productId) {
        for (OrderItem o : items) {
            if (o.getProductEntity().getId().equals(productId)) {
                o.incrementQuantity();
                recalculate();
                return;
            }
        }
        ProductEntity productEntity = productService.findById(productId).orElseThrow(() ->
                new ResourceNotFoundException("ProductEntity with id=" + productId + " doesn't exists (cart)"));
        items.add(new OrderItem(productEntity));
        recalculate();
    }

    public void decrementOrRemove(Long productId) {
        Iterator<OrderItem> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItem o = iter.next();
            if (o.getProductEntity().getId().equals(productId)) {
                o.decrementQuantity();
                if (o.getQuantity() == 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId) {
        Iterator<OrderItem> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItem o = iter.next();
            if (o.getProductEntity().getId().equals(productId)) {
                iter.remove();
                recalculate();
                return;
            }
        }
    }

    public void recalculate() {
        price = 0;
        for (OrderItem o : items) {
            o.setPricePerProduct(o.getProductEntity().getPrice());
            o.setPrice(o.getProductEntity().getPrice() * o.getQuantity());
            price += o.getPrice();
        }
    }

    public void clear() {
        items.clear();
        recalculate();
    }

}
