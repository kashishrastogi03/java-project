//package com.nagarro.service;

import com.nagarro.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import com.nagarro.service.ProductService;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    @Mock
    private ProductService productService;

    @Mock
    private MultipartFile mockFile;

    private Product product;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks

        // Sample product
        product = new Product();
        product.setId(1);
        product.setTitle("Test Product");
        product.setSize(10);
        product.setQuantity(50);
    }

    @Test
    public void testSaveProduct() throws IOException {
        doNothing().when(productService).saveProduct(any(Product.class), any(MultipartFile.class));

        productService.saveProduct(product, mockFile);

        verify(productService, times(1)).saveProduct(product, mockFile);
    }

    @Test
    public void testUpdateProduct() throws IOException {
        doNothing().when(productService).updateProduct(any(Product.class), any(MultipartFile.class));

        productService.updateProduct(product, mockFile);

        verify(productService, times(1)).updateProduct(product, mockFile);
    }

    @Test
    public void testDeleteProduct() {
        doNothing().when(productService).deleteProduct(anyInt());

        productService.deleteProduct(1);

        verify(productService, times(1)).deleteProduct(1);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> productList = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(productList);

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() {
        when(productService.getProductById(1)).thenReturn(product);

        Product result = productService.getProductById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(productService, times(1)).getProductById(1);
    }

    @Test
    public void testGetProductsByTitle() {
        Product product1 = new Product();
        product1.setTitle("Test Product");
        List<Product> productList = Arrays.asList(product1);

        when(productService.getProductsByTitle("Test Product")).thenReturn(productList);

        List<Product> result = productService.getProductsByTitle("Test Product");

        assertEquals(1, result.size());
        verify(productService, times(1)).getProductsByTitle("Test Product");
    }

    @Test
    public void testGetProductsBySize() {
        Product product1 = new Product();
        product1.setSize(10);
        List<Product> productList = Arrays.asList(product1);

        when(productService.getProductsBySize(10)).thenReturn(productList);

        List<Product> result = productService.getProductsBySize(10);

        assertEquals(1, result.size());
        verify(productService, times(1)).getProductsBySize(10);
    }

    @Test
    public void testGetProductsByQuantityRange() {
        Product product1 = new Product();
        product1.setQuantity(50);
        List<Product> productList = Arrays.asList(product1);

        when(productService.getProductsByQuantityRange(40, 60)).thenReturn(productList);

        List<Product> result = productService.getProductsByQuantityRange(40, 60);

        assertEquals(1, result.size());
        verify(productService, times(1)).getProductsByQuantityRange(40, 60);
    }
}
