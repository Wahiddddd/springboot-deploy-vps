package com.dibimbing.deployment.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dibimbing.deployment.entity.Product;
import com.dibimbing.deployment.repository.ProductRepository;

@Service
public class ProductService {

    private static final String PRODUCTS_CACHE = "products";
    private static final String ALL_PRODUCTS_KEY = "all";

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = PRODUCTS_CACHE, key = "'" + ALL_PRODUCTS_KEY + "'")
    public List<Product> getAllProducts() {
        return productRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Transactional(readOnly = true)
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }

    @Transactional
    @CacheEvict(cacheNames = PRODUCTS_CACHE, key = "'" + ALL_PRODUCTS_KEY + "'")
    public Product createProduct(String name, BigDecimal price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is required");
        }
        if (price == null || price.signum() < 0) {
            throw new IllegalArgumentException("price must be >= 0");
        }
        return productRepository.save(new Product(name.trim(), price));
    }

    @Transactional
    @CacheEvict(cacheNames = PRODUCTS_CACHE, key = "'" + ALL_PRODUCTS_KEY + "'")
    public Product updateProduct(Long id, String name, BigDecimal price) {
        Product product = getProduct(id);

        if (name != null && !name.isBlank()) {
            product.setName(name.trim());
        }
        if (price != null) {
            if (price.signum() < 0) {
                throw new IllegalArgumentException("price must be >= 0");
            }
            product.setPrice(price);
        }

        return productRepository.save(product);
    }

    @Transactional
    @CacheEvict(cacheNames = PRODUCTS_CACHE, key = "'" + ALL_PRODUCTS_KEY + "'")
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found: " + id);
        }
        productRepository.deleteById(id);
    }
}
