package com.example.infrastructure.persistence.mapper;

import com.example.domain.model.Product;
import com.example.infrastructure.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper {

    ProductEntity toEntity(Product product);

    Product toDomain(ProductEntity entity);
}
