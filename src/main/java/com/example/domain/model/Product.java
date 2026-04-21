package com.example.domain.model;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Builder(toBuilder = true)
public class Product {

    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Integer stock;

    @Builder
    protected Product(Long id, String name, String description, BigDecimal price, Integer stock) {
        this.id = id;
        this.name = Objects.requireNonNull(name, "Name is required");
        this.description = description;
        this.price = Objects.requireNonNull(price, "Price is required");
        this.stock = stock != null ? stock : 0;

        if (this.price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
    }


    public Product withUpdatedInfo(String name, String description, BigDecimal price, Integer stock) {
        return this.toBuilder()
                .name(name)
                .description(description)
                .price(price)
                .stock(stock)
                .build();
    }
}