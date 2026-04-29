package com.example.ProTrack.Controller;

import com.example.ProTrack.Exception.ResourceNotFoundException;
import com.example.ProTrack.Model.Product;
import com.example.ProTrack.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    // ✅ Get all products
    @GetMapping
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ✅ Get product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    // ✅ Create new product
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    // ✅ Update product
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        product.setName(productDetails.getName());
        product.setQrCode(productDetails.getQrCode());
        product.setBarcode(productDetails.getBarcode());
        product.setStatus(productDetails.getStatus());
        product.setProductionBatch(productDetails.getProductionBatch());

        return productRepository.save(product);
    }

    // ✅ Delete product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        productRepository.delete(product);
        return "Product with id " + id + " deleted successfully.";
    }

    // ✅ Get products by status (READY, DISPATCHED)
    @GetMapping("/status/{status}")
    public List<Product> getProductsByStatus(@PathVariable String status) {
        return productRepository.findByStatusIgnoreCase(status);
    }

    // ✅ Get product by barcode
    @GetMapping("/barcode/{barcode}")
    public Product getProductByBarcode(@PathVariable String barcode) {
        return productRepository.findByBarcode(barcode);
    }

    // ✅ Get product by QR code
    @GetMapping("/qrcode/{qrCode}")
    public Product getProductByQrCode(@PathVariable String qrCode) {
        return productRepository.findByQrCode(qrCode);
    }
}
