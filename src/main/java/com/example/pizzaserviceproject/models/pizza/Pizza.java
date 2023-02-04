package com.example.pizzaserviceproject.models.pizza;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name="pizza")
public class Pizza {
    @Id //primary key
    private final String id;
    private String name;
    private String size;
    private int cheese;
    private int pepperoni;
    private int ham;
    private String description;


    public Pizza() {
        this(
                "",
                "",
                0,
                0,
                0,
                ""
                );
    }

    public Pizza(String name, String size, int cheese, int pepperoni, int ham, String description) {
        this(
                UUID.randomUUID().toString(),
                name,
                size,
                cheese,
                pepperoni,
                ham,
                description);
    }

    public Pizza(String id, String name, String size, int cheese, int pepperoni, int ham, String description) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.cheese = cheese;
        this.pepperoni = pepperoni;
        this.ham = ham;
        this.description = description;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getCheese() {
        return cheese;
    }

    public void setCheese(int cheese) {
        this.cheese = cheese;
    }

    public int getPepperoni() {
        return pepperoni;
    }

    public void setPepperoni(int pepperoni) {
        this.pepperoni = pepperoni;
    }

    public int getHam() {
        return ham;
    }

    public void setHam(int ham) {
        this.ham = ham;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
