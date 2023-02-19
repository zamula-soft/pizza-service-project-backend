package com.example.pizzaserviceproject.entity;
import com.example.pizzaserviceproject.entity.Cafe;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Entity
@Table(name="pizza")
@Data
@NoArgsConstructor
public class Pizza {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cafe_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Cafe cafe;
    @NotBlank(message = "Name is mandatory")
    @Column(length = 150, nullable = false, unique = false)
    private String name;
    @NotBlank(message = "Size is mandatory")
    @Column(length = 2, nullable = true, unique = false)
    private String size;
    private String description;
    private String key_ingredients;

    @NotBlank(message = "Price is mandatory")
    @Column(columnDefinition = "Decimal(10,2) default '0.00' ", nullable = false)
    private BigDecimal price;
    @Column(columnDefinition = "Boolean default 'false' ")
    private Boolean isSpicy;

    public Pizza(String name, String size, String description, String key_ingredients, BigDecimal price, Boolean isSpicy) {
//        this.cafe = cafe;
        this.name = name;
        this.size = size;
        this.description = description;
        this.key_ingredients = key_ingredients;
        this.price = price;
        this.isSpicy = isSpicy;
    }
}
