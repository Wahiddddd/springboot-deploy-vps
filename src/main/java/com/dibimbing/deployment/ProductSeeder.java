package com.dibimbing.deployment;

import java.math.BigDecimal;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.dibimbing.deployment.repository.ProductRepository;
import com.dibimbing.deployment.entity.Product;

@Configuration
@Profile("dev")
public class ProductSeeder {

    @Bean
    ApplicationRunner seedProducts(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() > 0) {
                return;
            }

            productRepository.save(new Product("Coffee", new BigDecimal("35000.00")));
            productRepository.save(new Product("Tea", new BigDecimal("20000.00")));
            productRepository.save(new Product("Milk", new BigDecimal("18000.00")));
        };
    }
}
