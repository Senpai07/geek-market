package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.entities.OrderItem;
import com.geekbrains.geek.market.repositories.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private OrderRepository orderRepository;

    public void addOrSaveOrder(Order order) {
        for (OrderItem orderItem : order.getItems()) {
            orderItem.setOrder(order);
        }
        orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
