//package com.nagarro.service;
//
//import com.nagarro.model.Product;
//import com.nagarro.repository.ProductDAO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.*;
//import java.util.List;
//
//@Service
//public class ProductServiceImpl implements ProductService {
//
//    private final ProductDAO productDao;
//
//    @Autowired
//    public ProductServiceImpl(ProductDAO productDao) {
//        this.productDao = productDao;
//    }
//
//    private final String UPLOAD_DIR = "src/main/webapp/images/";
//
//    @Override
//   public void save(Product product) {
//        System.out.println("Saving");
//        productDao.save(product);  // JPA/Hibernate will handle insert/update
//    }
//
//    @Override
//    public void saveProduct(Product product, MultipartFile file) throws IOException {
//        System.out.println("Saving.....");
//
//        try {
//            if (!file.isEmpty()) {
//                String filename = file.getOriginalFilename();
//                Path path = Paths.get(UPLOAD_DIR + filename);
//                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//                product.setImage("/images/" + filename);
//            } else {
//                product.setImage("/images/default.png"); // fallback
//            }
//            System.out.println("product is being");
//            productDao.save(product);
//        } catch (Exception e) {
//            e.printStackTrace(); // print exception
//            throw e;
//        }
//    }
//
//    @Override
//    public void updateProduct(Product product, MultipartFile file) throws IOException {
//        System.out.println("UPdating");
//        if (!file.isEmpty()) {
//            String filename = file.getOriginalFilename();
//            Path path = Paths.get(UPLOAD_DIR + filename);
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//            product.setImage("/images/" + filename);
//        }
//        productDao.save(product);
//    }
//
//    @Override
//    public void deleteProduct(int id) {
//        if (productDao.existsById(id)) {
//            System.out.println("hello my dear");
//            productDao.deleteById(id);
//        } else {
//            throw new IllegalArgumentException("Product with ID " + id + " does not exist.");
//        }
//    }
//
//    @Override
//    public List<Product> getAllProducts() {
//        return productDao.findAll();
//    }
//
//    @Override
//    public Product getProductById(int id) {
//        return productDao.findById(id).orElse(null);
//    }
//
//    @Override
//    public List<Product> getProductsByTitle(String title) {
//        return productDao.findByTitleIgnoreCaseContaining(title);
//    }
//
//    @Override
//    public List<Product> getProductsBySize(int size) {
//        return productDao.findBySize(size);
//    }
//
//    @Override
//    public List<Product> getProductsByQuantityRange(int minQuantity, int maxQuantity) {
//        return productDao.findByQuantityBetween(minQuantity, maxQuantity);
//    }
//}


package com.nagarro.service;

import com.nagarro.model.Product;
import com.nagarro.repository.ProductDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDao;

    @Autowired
    public ProductServiceImpl(ProductDAO productDao) {
        this.productDao = productDao;
    }

    @Override
    public void save(Product product) {
        productDao.save(product);  // Save product without image
    }

    @Override
    public void updateProduct(Product product) {
        productDao.save(product);  // Update product without image
    }

    @Override
    public void deleteProduct(int id) {
        if (productDao.existsById(id)) {
            productDao.deleteById(id);
        } else {
            throw new IllegalArgumentException("Product with ID " + id + " does not exist.");
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @Override
    public Product getProductById(int id) {
        return productDao.findById(id).orElse(null);
    }

    @Override
    public List<Product> getProductsByTitle(String title) {
        return productDao.findByTitleIgnoreCaseContaining(title);
    }

    @Override
    public List<Product> getProductsBySize(int size) {
        return productDao.findBySize(size);
    }

    @Override
    public List<Product> getProductsByQuantityRange(int minQuantity, int maxQuantity) {
        return productDao.findByQuantityBetween(minQuantity, maxQuantity);
    }

	@Override
	public void saveProduct(Product product) throws IOException {
		// TODO Auto-generated method stub
		
	}
}

