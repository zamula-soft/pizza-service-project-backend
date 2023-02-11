package com.example.pizzaserviceproject.models.cafe;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Time;
import java.util.UUID;

@Entity
@Table(name = "cafe")
public class Cafe {
    @Id //primary key
    private String id;
    private Integer rating;
    private String name;
    private String city;
    private String country;
    private String address;
    private String photo;

    private String email;
    private String site;
    private String facebook;
    private String instagram;
    private String phone;
    private Boolean delivery;
    private String style;
    private String description;

    private Time open_at;
    private Time close_at;


    public Cafe(String id, Integer rating, String name, String city, String country, String address, String photo,
                String email, String site, String facebook, String instagram, String phone, Boolean delivery,
                String style, String description, Time open_at, Time close_at) {
        this.id = id;
        this.rating = rating;
        this.name = name;
        this.city = city;
        this.country = country;
        this.address = address;
        this.photo = photo;
        this.email = email;
        this.site = site;
        this.facebook = facebook;
        this.instagram = instagram;
        this.phone = phone;
        this.delivery = delivery;
        this.style = style;
        this.description = description;
        this.open_at = open_at;
        this.close_at = close_at;
    }

    public Cafe(Integer rating, String name, String city, String country, String address, String photo, String email,
                String site, String facebook, String instagram, String phone, Boolean delivery, String style,
                String description, Time open_at, Time close_at) {
        this(
                UUID.randomUUID().toString(),
                rating,
                name,
                city,
                country,
                address,
                photo,
                email,
                site,
                facebook,
                instagram,
                phone,
                delivery,
                style,
                description,
                open_at,
                close_at);
    }

    public Cafe(String id, Integer rating, String name, String city, String email, String phone, Time open_at, Time close_at) {
        this.id = id;
        this.rating = rating;
        this.name = name;
        this.city = city;
        this.email = email;
        this.phone = phone;
        this.open_at = open_at;
        this.close_at = close_at;
    }


    public Cafe() {
        this(0, "", "", "", "", null, null);
    }

    //    https://www.50toppizza.it/
    public Cafe(Integer rating, String name, String city, String email, String phone, Time open_at, Time close_at) {
        this(
                UUID.randomUUID().toString(),
                rating,
                name,
                city,
                email,
                phone,
                open_at,
                close_at);
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

    public void setId(String id) {
        this.id = id;
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


    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public Boolean getDelivery() {
        return delivery;
    }

    public void setDelivery(Boolean delivery) {
        this.delivery = delivery;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
