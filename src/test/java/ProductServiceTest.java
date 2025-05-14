//import com.nagarro.model.Product;
//import com.nagarro.repository.ProductDAO;
//import com.nagarro.service.ProductService;
//import com.nagarro.service.ProductServiceImpl;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.*;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ProductServiceTest {
//
//    @Mock
//    private ProductDAO productDao;
//
//    private ProductService productService;
//    private Product product;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);  // Initialize mocks
//
//        // Initialize the service with mocked DAO
//        productService = new ProductServiceImpl(productDao);
//
//        // Sample product
//        product = new Product();
//        product.setId(1);
//        product.setTitle("Test Product");
//        product.setSize(10);
//        product.setQuantity(50);
//    }
//
//    @Test
//    public void testSaveProduct() {
//        // Mock the DAO behavior
//        doNothing().when(productDao).save(any(Product.class));
//
//        try {
//			productService.saveProduct(product);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        // Verify that the DAO's save method was called
//        verify(productDao, times(1)).save(any(Product.class));
//    }
//
//    @Test
//    public void testUpdateProduct() {
//        // Mock the DAO behavior
//        doNothing().when(productDao).save(any(Product.class));
//
//        try {
//			productService.updateProduct(product);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//        // Verify that the DAO's save method was called
//        verify(productDao, times(1)).save(any(Product.class));
//    }
//
//    @Test
//    public void testDeleteProduct() {
//        // Mock the DAO behavior
//        doNothing().when(productDao).deleteById(anyInt());
//
//        productService.deleteProduct(1);
//
//        // Verify that the DAO's delete method was called
//        verify(productDao, times(1)).deleteById(1);
//    }
//
//    @Test
//    public void testGetAllProducts() {
//        Product product1 = new Product();
//        Product product2 = new Product();
//        List<Product> productList = Arrays.asList(product1, product2);
//
//        when(productDao.findAll()).thenReturn(productList);
//
//        List<Product> products = productService.getAllProducts();
//
//        assertEquals(2, products.size());
//        verify(productDao, times(1)).findAll();
//    }
//
//    @Test
//    public void testGetProductById() {
//        when(productDao.findById(1)).thenReturn(java.util.Optional.of(product));
//
//        Product result = productService.getProductById(1);
//
//        assertNotNull(result);
//        assertEquals(1, result.getId());
//        verify(productDao, times(1)).findById(1);
//    }
//
//    @Test
//    public void testGetProductsByTitle() {
//        Product product1 = new Product();
//        product1.setTitle("Test Product");
//        List<Product> productList = Arrays.asList(product1);
//
//        when(productDao.findByTitleIgnoreCaseContaining("Test Product")).thenReturn(productList);
//
//        List<Product> result = productService.getProductsByTitle("Test Product");
//
//        assertEquals(1, result.size());
//        verify(productDao, times(1)).findByTitleIgnoreCaseContaining("Test Product");
//    }
//
//    @Test
//    public void testGetProductsBySize() {
//        Product product1 = new Product();
//        product1.setSize(10);
//        List<Product> productList = Arrays.asList(product1);
//
//        when(productDao.findBySize(10)).thenReturn(productList);
//
//        List<Product> result = productService.getProductsBySize(10);
//
//        assertEquals(1, result.size());
//        verify(productDao, times(1)).findBySize(10);
//    }
//
//    @Test
//    public void testGetProductsByQuantityRange() {
//        Product product1 = new Product();
//        product1.setQuantity(50);
//        List<Product> productList = Arrays.asList(product1);
//
//        when(productDao.findByQuantityBetween(40, 60)).thenReturn(productList);
//
//        List<Product> result = productService.getProductsByQuantityRange(40, 60);
//
//        assertEquals(1, result.size());
//        verify(productDao, times(1)).findByQuantityBetween(40, 60);
//    }
//}
