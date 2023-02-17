package com.example.pizzaserviceproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.UUID;

@Entity
@Table(name = "cafe")
@Data
@NoArgsConstructor
public class Cafe {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer rating;
    @NotBlank
    @Column(name = "TITLE", length = 100, nullable = false, unique = false)
    private String name;
    @Column(length = 100, unique = false)
    private String city;
    @Column(length = 100, unique = false)
    private String country;
    private String address;
    @Column(length = 50, unique = false)
    private String email;
    @Column(length = 50, unique = false)
    private String site;
    @Column(length = 100, unique = false)
    private String facebook;
    @Column(length = 50, unique = false)
    private String phone;
    @Column(columnDefinition = "Boolean default 'false'")
    private Boolean delivery;
    @Column(length = 50, unique = false)
    private String style;
    private String description;

    private Time open_at;
    private Time close_at;

    public Cafe(Integer rating, String name, String city, String country, String address, String email, String site, String facebook, String phone, Boolean delivery, String style, String description, Time open_at, Time close_at) {
        this.rating = rating;
        this.name = name;
        this.city = city;
        this.country = country;
        this.address = address;
        this.email = email;
        this.site = site;
        this.facebook = facebook;
        this.phone = phone;
        this.delivery = delivery;
        this.style = style;
        this.description = description;
        this.open_at = open_at;
        this.close_at = close_at;
    }

    //    https://www.50toppizza.it/


}
