package com.example.infrastructure.web.dto;

import lombok.Data;


@Data
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stock;
    private boolean inStock;
}
