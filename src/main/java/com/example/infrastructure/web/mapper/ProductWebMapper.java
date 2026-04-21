package com.example.infrastructure.web.mapper;

import com.example.domain.model.Product;
import com.example.infrastructure.web.dto.ProductRequest;
import com.example.infrastructure.web.dto.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



public interface ProductWebMapper {

    @Mapping(target = "id", ignore = true)
    Product toDomain(ProductRequest request);

    @Mapping(target = "inStock", expression = "java(product.isInStock())")
    ProductResponse toResponse(Product product);
}
