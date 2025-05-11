import com.nagarro.model.Product;
import com.nagarro.repository.ProductDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.web.multipart.MultipartFile;
import com.nagarro.service.ProductServiceImpl;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceImpITest {

    @Mock
    private ProductDAO productDao;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveProduct_WithFile() throws IOException {
        Product product = new Product();
        MultipartFile file = mock(MultipartFile.class);

        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("test.png");
        when(file.getInputStream()).thenReturn(new ByteArrayInputStream("dummy data".getBytes()));

        productService.saveProduct(product, file);

        assertEquals("/images/test.png", product.getImage());
        verify(productDao).save(product);
    }

    @Test
    public void testSaveProduct_WithoutFile() throws IOException {
        Product product = new Product();
        MultipartFile file = mock(MultipartFile.class);

        when(file.isEmpty()).thenReturn(true);

        productService.saveProduct(product, file);

        assertEquals("/images/default.png", product.getImage());
        verify(productDao).save(product);
    }

    @Test
    public void testUpdateProduct_WithFile() throws IOException {
        Product product = new Product();
        MultipartFile file = mock(MultipartFile.class);

        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("update.png");
        when(file.getInputStream()).thenReturn(new ByteArrayInputStream("data".getBytes()));

        productService.updateProduct(product, file);

        assertEquals("/images/update.png", product.getImage());
        verify(productDao).save(product);
    }

    @Test
    public void testDeleteProduct_Exists() {
        when(productDao.existsById(1)).thenReturn(true);

        productService.deleteProduct(1);

        verify(productDao).deleteById(1);
    }

    @Test
    public void testDeleteProduct_NotExists() {
        when(productDao.existsById(999)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.deleteProduct(999);
        });

        assertTrue(exception.getMessage().contains("Product with ID 999 does not exist."));
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setId(1);

        when(productDao.findById(1)).thenReturn(Optional.of(product));

        Product result = productService.getProductById(1);

        assertEquals(1, result.getId());
    }

    @Test
    public void testGetAllProducts() {
        List<Product> mockList = Arrays.asList(new Product(), new Product());
        when(productDao.findAll()).thenReturn(mockList);

        List<Product> result = productService.getAllProducts();

        assertEquals(2, result.size());
    }

    @Test
    public void testGetProductsByTitle() {
        List<Product> products = List.of(new Product());
        when(productDao.findByTitleIgnoreCaseContaining("phone")).thenReturn(products);

        List<Product> result = productService.getProductsByTitle("phone");

        assertEquals(1, result.size());
    }

    @Test
    public void testGetProductsBySize() {
        List<Product> products = List.of(new Product());
        when(productDao.findBySize(42)).thenReturn(products);

        List<Product> result = productService.getProductsBySize(42);

        assertEquals(1, result.size());
    }

    @Test
    public void testGetProductsByQuantityRange() {
        List<Product> products = List.of(new Product(), new Product());
        when(productDao.findByQuantityBetween(10, 50)).thenReturn(products);

        List<Product> result = productService.getProductsByQuantityRange(10, 50);

        assertEquals(2, result.size());
    }
}
