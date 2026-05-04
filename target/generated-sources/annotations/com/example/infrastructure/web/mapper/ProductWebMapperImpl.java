package com.example.infrastructure.web.mapper;

import com.example.domain.model.Product;
import com.example.infrastructure.web.dto.ProductRequest;
import com.example.infrastructure.web.dto.ProductResponse;
import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-05-04T11:00:06+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.6 (Amazon.com Inc.)"
)
@Component
public class ProductWebMapperImpl implements ProductWebMapper {

    @Override
    public Product toDomain(ProductRequest request) {
        if ( request == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( request.getName() );
        product.description( request.getDescription() );
        if ( request.getPrice() != null ) {
            product.price( BigDecimal.valueOf( request.getPrice() ) );
        }
        product.stock( request.getStock() );

        return product.build();
    }

    @Override
    public ProductResponse toResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();

        productResponse.setId( product.getId() );
        productResponse.setName( product.getName() );
        productResponse.setDescription( product.getDescription() );
        if ( product.getPrice() != null ) {
            productResponse.setPrice( product.getPrice().doubleValue() );
        }
        productResponse.setStock( product.getStock() );
        productResponse.setInStock( product.isInStock() );

        return productResponse;
    }
}
