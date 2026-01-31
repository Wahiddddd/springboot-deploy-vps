package com.dibimbing.deployment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dibimbing.deployment.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
