package com.example.projet_java_fullstack.repository;

import com.example.projet_java_fullstack.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    Optional<Categorie> findByName(String name);
    boolean existsByName(String name);
}
