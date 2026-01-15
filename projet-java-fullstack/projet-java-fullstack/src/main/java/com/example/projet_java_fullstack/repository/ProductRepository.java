package com.example.projet_java_fullstack.repository;

import com.example.projet_java_fullstack.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Product> findByCategory_NameContainingIgnoreCase(String categoryName, Pageable pageable);
    Page<Product> findByNameContainingIgnoreCaseAndCategory_NameContainingIgnoreCase(String name, String categoryName, Pageable pageable);
}
