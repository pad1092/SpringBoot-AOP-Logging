package com.pad.loggingwithaop.repository;

import com.pad.loggingwithaop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}