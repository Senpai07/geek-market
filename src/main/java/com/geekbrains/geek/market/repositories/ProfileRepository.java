package com.geekbrains.geek.market.repositories;


import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.entities.Profile;
import com.geekbrains.geek.market.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    @Query("select p from Profile p where p.user.username = ?1")
    Optional<Profile> findByUsername(String username);
}
