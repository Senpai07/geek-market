package com.geekbrains.geek.market.repositories;

import com.geekbrains.geek.market.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("select u from User u join fetch u.roles where u.id =?1 ")
    User findByIdWithRoles(Long id);


}
