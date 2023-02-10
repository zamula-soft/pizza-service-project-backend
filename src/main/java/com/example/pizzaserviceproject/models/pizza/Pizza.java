package com.example.pizzaserviceproject.models.pizza;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name="pizza")
public class Pizza {
    @Id //primary key
    private String id;
    @NotBlank
    private String name;
    private String size;
    private String description;
    private Boolean spicy;


    public Pizza() {
        this(
                "",
                "",
                "",
                "",
                false
                );
    }

    public Pizza(String name, String size, String description) {
        this(
                UUID.randomUUID().toString(),
                name,
                size,
                description, false);
    }

    public Pizza(String name, String size, String description, Boolean spicy) {
        this(
                UUID.randomUUID().toString(),
                name,
                size,
                description, spicy);
    }

    public Pizza(String id, String name, String size, String description, Boolean spicy) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.description = description;
        this.spicy = spicy;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSpicy() {
        return spicy;
    }

    public void setSpicy(Boolean spicy) {
        this.spicy = spicy;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }
}
