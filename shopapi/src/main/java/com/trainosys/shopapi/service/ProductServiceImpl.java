package com.trainosys.shopapi.service;

import com.trainosys.shopapi.model.Product;
import com.trainosys.shopapi.repository.ProductRepository;
import com.trainosys.shopapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Product not found"
                )
        );
        return product;
    }

    @Override
    public List<Product> getProductByCategory(String category) {

        return productRepository.findByCategoryIgnoreCase(category);
    }

    @Override
    public Product createProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        Product existingProduct = getProductById(productId);

        if (product.getProductName() != null) {
            existingProduct.setProductName(product.getProductName());
        }
        if (product.getCategory() != null) {
            existingProduct.setCategory(product.getCategory());
        }
        if (product.getStock() != 0) { // or check null if wrapper Integer is used
            existingProduct.setStock(product.getStock());
        }
        if (product.getPrice() != null) {
            existingProduct.setPrice(product.getPrice());
        }

        return productRepository.save(existingProduct);
    }

    @Override
    public Product updateStock(Long productId, int quantity) {
        Product existingProduct = getProductById(productId);
        existingProduct.setStock(quantity);
        return productRepository.save(existingProduct);
    }

    @Override
    public String deleteProduct(Long productId) {
        Product existingProduct = getProductById(productId);
        productRepository.delete(existingProduct);
        return "Product deleted successfully.";
    }
}
