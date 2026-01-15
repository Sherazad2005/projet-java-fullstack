package com.example.projet_java_fullstack.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime orderDate = LocalDateTime.now();

    private double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @ManyToOne(optional = false)
    @JoinColumn(name = "produit_id")
    private Product produit;

    private int quantite;

    public Order() {}

    public Order(Long id, User user, LocalDateTime orderDate, double totalAmount, OrderStatus status, Product produit, int quantite) {
        this.id = id;
        this.user = user;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.produit = produit;
        this.quantite = quantite;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public Product getProduit() { return produit; }
    public void setProduit(Product produit) { this.produit = produit; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }
}


