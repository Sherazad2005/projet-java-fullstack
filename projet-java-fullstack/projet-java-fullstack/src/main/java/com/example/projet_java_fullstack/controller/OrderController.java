package com.example.projet_java_fullstack.controller;

import com.example.projet_java_fullstack.model.Order;
import com.example.projet_java_fullstack.model.OrderStatus;
import com.example.projet_java_fullstack.model.Product;
import com.example.projet_java_fullstack.model.Users;
import com.example.projet_java_fullstack.repository.OrderRepository;
import com.example.projet_java_fullstack.repository.ProductRepository;
import com.example.projet_java_fullstack.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepo;
    private final UserRepository userRepo;
    private final ProductRepository productRepo;

    public OrderController(OrderRepository orderRepo, UserRepository userRepo, ProductRepository productRepo) {
        this.orderRepo = orderRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    public static class CreateOrderRequest {
        public Long productId;
        public int quantite;
    }

    @PostMapping
    public Order createOrder(@RequestBody CreateOrderRequest req, Authentication auth) {
        String username = auth.getName();

        Users user = userRepo.findByUsername(username).orElseThrow();
        Product product = productRepo.findById(req.productId).orElseThrow();

        Order order = new Order();
        order.setUser(user);
        order.setProduit(product);
        order.setQuantite(req.quantite);
        order.setStatus(OrderStatus.PENDING);
        
        BigDecimal total = product.getPrice().multiply(BigDecimal.valueOf(req.quantite));
        order.setTotalAmount(total);

        return orderRepo.save(order);
    }

    @GetMapping("/my-orders")
    public List<Order> myOrders(Authentication auth) {
        String username = auth.getName();
        Users user = userRepo.findByUsername(username).orElseThrow();
        return orderRepo.findByUser_Id(user.getId());
    }
}

