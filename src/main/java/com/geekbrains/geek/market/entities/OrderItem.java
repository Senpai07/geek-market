package com.geekbrains.geek.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@NoArgsConstructor
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_product")
    private int pricePerProduct;

    @Column(name = "price")
    private int price;

    public OrderItem(ProductEntity productEntity) {
        this.productEntity = productEntity;
        this.quantity = 1;
        this.price = productEntity.getPrice();
        this.pricePerProduct = productEntity.getPrice();
    }

    public void incrementQuantity() {
        quantity++;
        price = pricePerProduct * quantity;
    }

    public void decrementQuantity() {
        quantity--;
        price = pricePerProduct * quantity;
    }
}
