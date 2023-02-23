package com.example.pizzaserviceproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cafe")
@Data
@NoArgsConstructor
public class Cafe {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer rating;
    @NotBlank(message = "Title is mandatory")
    @Column(name = "TITLE", length = 100, nullable = false)
    private String name;
    @Column(length = 100)
    private String city;
    @Column(length = 100)
    private String country;
    private String address;
    @Column(length = 50)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be email")
    private String email;
    @Column(length = 50)
    private String site;
    @Column(length = 100)
    private String facebook;
    @Column(length = 50)
    private String phone;
    @Column(columnDefinition = "Boolean default 'false'")
    private Boolean hasDelivery;
    private String description;

    private Time open_at;
    private Time close_at;


    public Cafe(Integer rating, String name, String city, String country, String address, String email, String site,
                String facebook, String phone, Boolean hasDelivery, String description, Time open_at, Time close_at) {
        this.rating = rating;
        this.name = name;
        this.city = city;
        this.country = country;
        this.address = address;
        this.email = email;
        this.site = site;
        this.facebook = facebook;
        this.phone = phone;
        this.hasDelivery = hasDelivery;
        this.description = description;
        this.open_at = open_at;
        this.close_at = close_at;
    }

}
