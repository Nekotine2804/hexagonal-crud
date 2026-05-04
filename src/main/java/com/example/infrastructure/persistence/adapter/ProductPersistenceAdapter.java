package com.example.infrastructure.persistence.adapter;

import com.example.domain.model.Product;
import com.example.application.port.out.ProductRepository;
import com.example.infrastructure.persistence.mapper.ProductPersistenceMapper;
import com.example.infrastructure.persistence.repository.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductPersistenceMapper mapper;

    @Override
    public Product save(Product product) {
        var entity = mapper.toEntity(product);
        var saved = productJpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productJpaRepository.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return productJpaRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        productJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return productJpaRepository.existsById(id);
    }
}
