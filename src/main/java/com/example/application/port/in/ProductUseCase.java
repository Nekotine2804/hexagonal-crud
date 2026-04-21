package com.example.application.port.in;

import com.example.domain.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductUseCase {

    Product createProduct(Product product);

    Optional<Product> getProductById(Long id);

    List<Product> getAllProducts();

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

}