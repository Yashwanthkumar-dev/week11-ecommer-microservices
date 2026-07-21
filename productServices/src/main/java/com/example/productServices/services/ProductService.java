package com.example.productServices.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.productServices.entity.ProductEntity;
import com.example.productServices.exception.ResourceNotFoundException;
import com.example.productServices.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    // ========= Reuse code =================
    private ProductEntity getIdOrThrow(Long id) {
        return productRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("product was not found"));
    }

    // ============================ create product =========================
    public ResponseEntity<?> createProduct(String productName,
            String description, Double price, Integer stock,
            MultipartFile image) throws IOException {
        String uploadDir = "upload";

        File Directory = new File(uploadDir);

        if (!Directory.exists()) {

            Directory.mkdirs();
        }
        String imageName = image.getOriginalFilename();

        try {

            Path pathName = Paths.get(uploadDir, imageName);

            Files.write(pathName, image.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        ProductEntity product = new ProductEntity();
        product.setProductName(productName);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);
        product.setImage(imageName);
        product.setCreatedAt(LocalDateTime.now());

        productRepo.save(product);

        return ResponseEntity.status(200).body("product was created successfuly");
    }

    // ========================= view all products ==================
    public ResponseEntity<?> viewAllProducts() {
        List<ProductEntity> allProducts = productRepo.findAll();
        if (allProducts.isEmpty()) {
            return ResponseEntity.status(404).body("product was empty");
        }
        return ResponseEntity.status(200).body(allProducts);
    }

    // ======================= get product by id =================
    public ResponseEntity<?> getProductById(Long id) {
        ProductEntity isProduct = getIdOrThrow(id);
        return ResponseEntity.status(200).body(isProduct);

    }

    // ======================== update product by id ================
    public ResponseEntity<?> updateProductById(Long id, String productName, String description, Double price,
            Integer stock,
            MultipartFile image) {
        ProductEntity updateProduct = getIdOrThrow(id);
        updateProduct.setProductName(productName);
        updateProduct.setDescription(description);
        updateProduct.setPrice(price);
        updateProduct.setStock(stock);
        updateProduct.setUpdatedAt(LocalDateTime.now());
        String uploadDir = "upload";
        File Directory = new File(uploadDir);
        if (!Directory.exists()) {
            Directory.mkdirs();
        }

        try {
            if (updateProduct.getImage() != null) {
                File oldImage = new File(updateProduct.getImage());

                if (oldImage.exists()) {
                    oldImage.delete();
                }
            }
            String imagename = image.getOriginalFilename();
            Path imagePath = Paths.get(uploadDir, imagename);
            Files.write(imagePath, image.getBytes());
            updateProduct.setImage(imagename);
        } catch (Exception e) {
            e.printStackTrace();
        }

        productRepo.save(updateProduct);
        return ResponseEntity.status(200).body("updated succesfully");

    }

    // ====================== delete product by id ================================
    public ResponseEntity<?> deleteProductById(Long id) {
        ProductEntity isProduct = getIdOrThrow(id);
        productRepo.delete(isProduct);
        return ResponseEntity.status(200).body("product was deleted");

    }

}