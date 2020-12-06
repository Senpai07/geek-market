package com.geekbrains.geek.market;

import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.repositories.UserRepository;
import com.geekbrains.geek.market.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void findOneUserTest() {
        User userFromDB = new User();
        userFromDB.setUsername("user");
        userFromDB.setEmail("user@gmail.com");

        Mockito.doReturn(Optional.of(userFromDB))
                .when(userRepository)
                .findByUsername("user");

        User userJohn = userService.findByUsername("user").get();
        Assertions.assertNotNull(userJohn);
        Assertions.assertEquals("user@gmail.com", userJohn.getEmail());
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("user"));
    }

}
