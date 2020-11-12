package com.geekbrains.geek.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "profiles")
public class UserProfile {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "surname")
    private String surname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "gender")
    private String gender;

    @Column(name = "city")
    private String city;

    public UserProfile(Long id, String firstname, String surname, String phone, String email, Integer birthYear, String gender, String city) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.birthYear = birthYear;
        this.gender = gender;
        this.city = city;
    }
}