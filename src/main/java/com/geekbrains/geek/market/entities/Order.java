package com.geekbrains.geek.market.entities;

import com.geekbrains.geek.market.utils.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> items;

    @Column(name = "price")
    private int price;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "receiver_name")
    private String receiverName;

    public Order(User user, Cart cart, String address, String phone, String receiverName) {
        this.user = user;
        this.price = cart.getPrice();
        this.address = address;
        this.phone = phone;
        this.receiverName = receiverName;
        this.items = new ArrayList<>();
        cart.getItems().forEach(oi -> {
            oi.setOrder(this);
            items.add(oi);
        });
    }

}
