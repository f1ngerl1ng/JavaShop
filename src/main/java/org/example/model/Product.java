package org.example.model;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String productId;

    private String productName;
    private String productDescription;
    private double productPrice;
    private String productCondition;
    private String productStatus;
    private String productManufacturer;

    @Transient
    private MultipartFile productImage;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductCondition() {
        return productCondition;
    }

    public void setProductCondition(String productCondition) {
        this.productCondition = productCondition;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductManufacturer() {
        return productManufacturer;
    }

    public void setProductManufacturer(String productManufacturer) {
        this.productManufacturer = productManufacturer;
    }
    public MultipartFile getProductImage() {
        return productImage;
    }

    public void setProductImage(MultipartFile productImage) {
        this.productImage = productImage;
    }

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    private Category Category;

    public void setCategory(Category category) {
        Category = category;
    }
    public Category getCategory() {
        return Category;
    }

}
