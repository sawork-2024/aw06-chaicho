package com.micropos.product.service;

import com.micropos.product.model.Product;
import com.micropos.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(@Autowired ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> products() {
        return productRepository.allProducts();
    }

    @Override
    public Product getProduct(String id) {
        // Sleep for 0.5 second to simulate a slow service
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productRepository.findProduct(id);
    }

    @Override
    public Product randomProduct() {
        return null;
    }
}
