package com.geekbrains.geek.market.dto;

import com.geekbrains.geek.market.entities.OrderEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private int price;
    private String address;
    private String phone;
    private String receiverName;
    List<OrderItemDto> items;

    public OrderDto(OrderEntity o) {
        this.id = o.getId();
        this.price = o.getPrice();
        this.address = o.getAddress();
        this.phone = o.getPhone();
        this.receiverName = o.getReceiverName();
        this.items = o.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
    }

}
