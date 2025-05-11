package com.nagarro.controller;

import com.nagarro.model.Product;
import com.nagarro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/dashboard")
    public String showUserDashboard(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "product-dashboard";
    }


    @PostMapping("/add")
    public String addProduct(@ModelAttribute Product product,
                             @RequestParam("imageFile") MultipartFile file,
                             RedirectAttributes redirectAttributes) {
        try {
            productService.saveProduct(product, file);  // Store image + save product
            redirectAttributes.addFlashAttribute("success", "Product added successfully!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("error", "Failed to upload image!");
        }
        return "redirect:/product/dashboard";
    }

    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        productService.save(product);
        redirectAttributes.addFlashAttribute("success", "Product added successfully!");
        return "redirect:/product/dashboard";
    }

    @PostMapping("/edit")
    @ResponseBody
    public ResponseEntity<String> editProduct(@ModelAttribute Product product, @RequestParam("imageFile") MultipartFile file) {
        System.out.println("Hello friends");
        try {
            productService.updateProduct(product, file);
            return ResponseEntity.ok("Product updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating product.");
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete product");
        }
    }
}
