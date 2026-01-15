package com.example.projet_java_fullstack.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categorie category;

    @Column(nullable = false)
    private int stockQuantity;

    private LocalDateTime createdAt = LocalDateTime.now();

    private String lienImage;

    public Product() {}

    public Product(Long id, String name, String description, double price, Categorie category,
                   int stockQuantity, LocalDateTime createdAt, String lienImage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.createdAt = createdAt;
        this.lienImage = lienImage;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Categorie getCategory() { return category; }
    public void setCategory(Categorie category) { this.category = category; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getLienImage() { return lienImage; }
    public void setLienImage(String lienImage) { this.lienImage = lienImage; }
}


