//package com.nagarro.model;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Size;
//
//@Entity
//@Table(name = "products")
//public class Product {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer id;
//
//    @Pattern(regexp = "^[A-Za-z ]+$", message = "Title must contain only letters and spaces")
//
//    private String title;
//    private int quantity;
//    private int size;
//    private String image;
//    // Store image filename
//    @Size(max = 20, message = "Description cannot exceed 20 characters")
//    private String description;
//    private int price;
//
//    // Getters & Setters
//    public int getId() { return id; }
//    public void setId(int id) { this.id = id; }
//
//    public String getTitle() { return title; }
//    public void setTitle(String title) { this.title = title; }
//
//    public int getQuantity() { return quantity; }
//    public void setQuantity(int quantity) { this.quantity = quantity; }
//
//    public int getSize() { return size; }
//    public void setSize(int size) { this.size = size; }
//
//    ;
//
//    public String getDescription() {
//        return description;
//    }
//
//    //public void setDescription(String description) {
//     //   this.description = description;
//  //  }
//
//    public void setDescription(String description) {
//        if (description != null && description.length() > 200) {
//            this.description = description.substring(0, 200); // truncate
//        } else {
//            this.description = description;
//        }
//    }
//
//
//
//    public String getImage() { return image; }
//    public void setImage(String image) { this.image = image; }
//
//    public int getPrice() { return price; }
//    public void setPrice(int price) { this.price = price; }
//}


package com.nagarro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Pattern(regexp = "^[A-Za-z ]+$", message = "Title must contain only letters and spaces")
    private String title;

    private int quantity;
    private int size;

    @Size(max = 200, message = "Description cannot exceed 200 characters")
    private String description;

    private int price;

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        if (description != null && description.length() > 200) {
            this.description = description.substring(0, 200); // truncate
        } else {
            this.description = description;
        }
    }

    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
}

