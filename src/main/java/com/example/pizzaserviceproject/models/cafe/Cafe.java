package com.example.pizzaserviceproject.models.cafe;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Time;
import java.util.UUID;

@Entity
@Table(name="cafe")
public class Cafe {
    @Id //primary key
    private final String id;
    private String name;
    private String city;
    private String email;
    private String phone;
    private Time open_at;
    private Time close_at;

    public Cafe() {
        this("", "", "", "", null, null);
    }

    public Cafe(String name, String city, String email, String phone, Time open_at, Time close_at) {
        this(
                UUID.randomUUID().toString(),
                name,
                city,
                email,
                phone,
                open_at,
                close_at);
    }

    public Cafe(String id, String name, String city, String email, String phone, Time open_at, Time close_at) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.open_at = open_at;
        this.close_at = close_at;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Time getOpen_at() {
        return open_at;
    }

    public void setOpen_at(Time open_at) {
        this.open_at = open_at;
    }

    public Time getClose_at() {
        return close_at;
    }

    public void setClose_at(Time close_at) {
        this.close_at = close_at;
    }
}
