package com.example.pizzaserviceproject.models.menu;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Time;
import java.util.UUID;

@Entity
@Table(name="menu")
public class Menu {
    @Id //primary key
    private final String id;
    private String pizzaid;
    private String cafeid;

    private double price;
    private int amount;

    public Menu() {
        this("", "", "", 0.00, 0);
    }

    public Menu(String pizzaid, String cafeid, double price, int amount) {
        this(
                UUID.randomUUID().toString(),
                pizzaid,
                cafeid,
                price,
                amount);
    }

    public Menu(String id, String pizzaid, String cafeid, double price, int amount) {
        this.id = id;
        this.pizzaid = pizzaid;
        this.cafeid = cafeid;
        this.price = price;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getPizzaid() {
        return pizzaid;
    }

    public void setPizzaid(String pizzaid) {
        this.pizzaid = pizzaid;
    }

    public String getCafeid() {
        return cafeid;
    }

    public void setCafeid(String cafeid) {
        this.cafeid = cafeid;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
