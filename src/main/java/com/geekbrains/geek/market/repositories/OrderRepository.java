package com.geekbrains.geek.market.repositories;

import com.geekbrains.geek.market.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {
    @Query("select o from OrderEntity o where o.user.username = ?1")
    List<OrderEntity> findAllOrdersByUsername(String username);

}
