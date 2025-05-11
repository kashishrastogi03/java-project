package com.nagarro.repository;

import com.nagarro.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {
    List<Product> findByTitleIgnoreCaseContaining(String title);

    List<Product> findBySize(int size);

    List<Product> findByQuantityBetween(int minQuantity, int maxQuantity);
}
