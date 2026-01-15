package com.example.projet_java_fullstack.controller;

import com.example.projet_java_fullstack.model.Product;
import com.example.projet_java_fullstack.repository.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepo;

    public ProductController(ProductRepository productRepo) {
        this.productRepo = productRepo;

    }


    @GetMapping
    public Page<Product> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String dir
    ) {
        Sort.Direction direction = dir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));
        return productRepo.findAll(pageable);
    }


    @GetMapping("/{id}")
    public Product getOne(@PathVariable Long id) {
        return productRepo.findById(id).orElseThrow();
    }

    @GetMapping("/search")
    public Page<Product> search(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        String n = (name == null) ? "" : name;
        String c = (category == null) ? "" : category;

        if (!n.isBlank() && !c.isBlank()) {
            return productRepo.findByNameContainingIgnoreCaseAndCategory_NameContainingIgnoreCase(n, c, pageable);
        } else if (!n.isBlank()) {
            return productRepo.findByNameContainingIgnoreCase(n, pageable);
        } else if (!c.isBlank()) {
            return productRepo.findByCategory_NameContainingIgnoreCase(c, pageable);
        }
        return productRepo.findAll(pageable);
    }
}

