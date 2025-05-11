package com.nagarro.service;

import com.nagarro.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void saveProduct(Product product, MultipartFile file) throws IOException;
    void updateProduct(Product product, MultipartFile file) throws IOException;
    void deleteProduct(int id);
    List<Product> getAllProducts();
    Product getProductById(int id);
    List<Product> getProductsByTitle(String title);
    List<Product> getProductsBySize(int size);
    List<Product> getProductsByQuantityRange(int minQuantity, int maxQuantity);

        void save(Product product);  // Add this if not already present


}
