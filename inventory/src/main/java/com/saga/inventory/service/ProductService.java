package com.saga.inventory.service;

import com.saga.inventory.repository.ProductRepository;
import com.saga.inventory.repository.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findProductByProductId(Integer productId) {
        return productRepository.findByProductId(productId);
    }

    public Product decreaseProductCount(Product product, Integer productCount) {
        product.setProductCount(product.getProductCount() - productCount);
        return productRepository.save(product);
    }

    public Product increaseProductCount(Product product, Integer productCount) {
        product.setProductCount(product.getProductCount() + productCount);
        return productRepository.save(product);
    }
}
