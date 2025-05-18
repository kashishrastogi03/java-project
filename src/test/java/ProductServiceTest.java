import com.nagarro.model.Product;
import com.nagarro.repository.ProductDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.nagarro.service.ProductServiceImpl;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductDAO productDao;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1);
        product.setTitle("Test Product");
        product.setSize(5);
        product.setQuantity(100);
    }

    @Test
    void testSave() {
        productService.save(product);
        verify(productDao, times(1)).save(product);
    }

    @Test
    void testUpdateProduct() {
        productService.updateProduct(product);
        verify(productDao, times(1)).save(product);
    }

    @Test
    void testDeleteProduct() {
        when(productDao.existsById(1)).thenReturn(true);
        productService.deleteProduct(1);
        verify(productDao).deleteById(1);
    }

    @Test
    void testDeleteProduct_NotFound() {
        when(productDao.existsById(1)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, () -> productService.deleteProduct(1));
    }

    @Test
    void testGetAllProducts() {
        when(productDao.findAll()).thenReturn(Arrays.asList(product));
        List<Product> list = productService.getAllProducts();
        assertEquals(1, list.size());
    }

    @Test
    void testGetProductById() {
        when(productDao.findById(1)).thenReturn(Optional.of(product));
        Product p = productService.getProductById(1);
        assertEquals(product.getId(), p.getId());
    }

    @Test
    void testGetProductsByTitle() {
        when(productDao.findByTitleIgnoreCaseContaining("Test")).thenReturn(List.of(product));
        List<Product> result = productService.getProductsByTitle("Test");
        assertEquals(1, result.size());
    }

    @Test
    void testGetProductsBySize() {
        when(productDao.findBySize(5)).thenReturn(List.of(product));
        List<Product> result = productService.getProductsBySize(5);
        assertEquals(1, result.size());
    }

    @Test
    void testGetProductsByQuantityRange() {
        when(productDao.findByQuantityBetween(50, 150)).thenReturn(List.of(product));
        List<Product> result = productService.getProductsByQuantityRange(50, 150);
        assertEquals(1, result.size());
    }

    @Test
    void testSaveProduct_DefaultImplementation() throws IOException {
        productService.saveProduct(product);
    }
}
