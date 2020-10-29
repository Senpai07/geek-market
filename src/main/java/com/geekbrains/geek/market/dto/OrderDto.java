package com.geekbrains.geek.market.dto;

import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.entities.OrderItem;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.utils.Cart;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private int price;
    private String address;
    private String phone;
    private String receiverName;

    public OrderDto(Order o) {
        this.price = o.getPrice();
        this.address = o.getAddress();
        this.phone = o.getPhone();
        this.receiverName = o.getReceiverName();
    }

}
