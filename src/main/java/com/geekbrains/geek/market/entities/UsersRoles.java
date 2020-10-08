package com.geekbrains.geek.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "users_roles")
@NoArgsConstructor
@Data
public class UsersRoles {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public UsersRoles(User user, Role role) {
        this.user = user;
        this.role = role;
    }
}
