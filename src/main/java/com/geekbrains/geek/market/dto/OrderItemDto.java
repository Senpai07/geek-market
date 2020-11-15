package com.geekbrains.geek.market.dto;

import com.geekbrains.geek.market.entities.OrderItem;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDto {

    private Long productId;
    private String productTitle;
    private String categoryTitle;
    private int quantity;
    private int pricePerProduct;
    private int price;

    public OrderItemDto(OrderItem orderItem) {
        this.productId = orderItem.getProductEntity().getId();
        this.productTitle = orderItem.getProductEntity().getTitle();
        this.categoryTitle = orderItem.getProductEntity().getCategory().getTitle();
        this.quantity = orderItem.getQuantity();
        this.price = orderItem.getPrice();
        this.pricePerProduct = orderItem.getPricePerProduct();
    }
}
