package com.micropos.product.repository;

import com.micropos.product.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryRepository implements ProductRepository {

        @Override
        public List<Product> allProducts() {
            return List.of(
                    new Product("1", "Apple", 0.60,null),
                    new Product("2", "Banana", 0.20, null),
                    new Product("3", "Orange", 0.25, null),
                    new Product("4", "Pear", 0.50, null),
                    new Product("5", "Pineapple", 1.00, null)
            );
        }

        @Override
        public Product findProduct(String productId) {
            return allProducts().stream()
                    .filter(product -> product.getId().equals(productId))
                    .findFirst()
                    .orElse(null);
        }
}
