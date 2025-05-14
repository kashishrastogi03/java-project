//import com.nagarro.model.Product;
//import com.nagarro.repository.ProductDAO;
//import com.nagarro.service.ProductServiceImpl;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//public class ProductServiceImpITest {
//
//    @Mock
//    private ProductDAO productDao;
//
//    @InjectMocks
//    private ProductServiceImpl productService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testSaveProduct_WithoutFile() {
//        Product product = new Product();
//
//        try {
//			productService.saveProduct(product);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        verify(productDao).save(product);
//    }
//
//    @Test
//    public void testUpdateProduct_WithoutFile() {
//        Product product = new Product();
//
//        productService.updateProduct(product);
//
//        verify(productDao).save(product);
//    }
//
//    @Test
//    public void testDeleteProduct_Exists() {
//        when(productDao.existsById(1)).thenReturn(true);
//
//        productService.deleteProduct(1);
//
//        verify(productDao).deleteById(1);
//    }
//
//    @Test
//    public void testDeleteProduct_NotExists() {
//        when(productDao.existsById(999)).thenReturn(false);
//
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            productService.deleteProduct(999);
//        });
//
//        assertTrue(exception.getMessage().contains("Product with ID 999 does not exist."));
//    }
//
//    @Test
//    public void testGetProductById() {
//        Product product = new Product();
//        product.setId(1);
//
//        when(productDao.findById(1)).thenReturn(java.util.Optional.of(product));
//
//        Product result = productService.getProductById(1);
//
//        assertEquals(1, result.getId());
//    }
//
//    @Test
//    public void testGetAllProducts() {
//        List<Product> mockList = Arrays.asList(new Product(), new Product());
//        when(productDao.findAll()).thenReturn(mockList);
//
//        List<Product> result = productService.getAllProducts();
//
//        assertEquals(2, result.size());
//    }
//
//    @Test
//    public void testGetProductsByTitle() {
//        List<Product> products = List.of(new Product());
//        when(productDao.findByTitleIgnoreCaseContaining("phone")).thenReturn(products);
//
//        List<Product> result = productService.getProductsByTitle("phone");
//
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    public void testGetProductsBySize() {
//        List<Product> products = List.of(new Product());
//        when(productDao.findBySize(42)).thenReturn(products);
//
//        List<Product> result = productService.getProductsBySize(42);
//
//        assertEquals(1, result.size());
//    }
//
//    @Test
//    public void testGetProductsByQuantityRange() {
//        List<Product> products = List.of(new Product(), new Product());
//        when(productDao.findByQuantityBetween(10, 50)).thenReturn(products);
//
//        List<Product> result = productService.getProductsByQuantityRange(10, 50);
//
//        assertEquals(2, result.size());
//    }
//}
