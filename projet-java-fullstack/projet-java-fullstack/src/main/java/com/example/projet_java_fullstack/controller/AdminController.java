package com.example.projet_java_fullstack.controller;

import com.example.projet_java_fullstack.model.Product;
import com.example.projet_java_fullstack.model.Role;
import com.example.projet_java_fullstack.model.Users;
import com.example.projet_java_fullstack.repository.ProductRepository;
import com.example.projet_java_fullstack.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public AdminController(final ProductRepository productRepo, final UserRepository userRepo) {
        this.productRepo = productRepo;
        this.userRepo = userRepo;

    }


    @PostMapping("/products")
    public Product createProduct(@RequestBody Product p) {
        p.setId(null);
        return productRepo.save(p);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product p) {
        Product existing = productRepo.findById(id).orElseThrow();
        existing.setName(p.getName());
        existing.setDescription(p.getDescription());
        existing.setPrice(p.getPrice());
        existing.setStockQuantity(p.getStockQuantity());
        existing.setLienImage(p.getLienImage());
        existing.setCategory(p.getCategory());
        return productRepo.save(existing);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepo.deleteById(id);
    }


    @GetMapping("/users")
    public List<Users> allUsers() {
        return userRepo.findAll();
    }


    @PutMapping("/users/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users u) {
        Users existing = userRepo.findById(id).orElseThrow();
        existing.setUsername(u.getUsername());
        existing.setEmail(u.getEmail());
        existing.setEnabled(u.isEnabled());
        existing.setRoles(u.getRoles() == null ? Role.USER : u.getRoles());
        return userRepo.save(existing);
    }
}

