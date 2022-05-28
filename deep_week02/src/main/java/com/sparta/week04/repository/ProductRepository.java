package com.sparta.week04.repository;

import com.sparta.week04.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
        List<Product> findAllByUserId(Long userId);
}