package org.example.domain;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    void addProduct(Product product);
    Optional<Product> findById(int id);
    List<Product> findAllProducts();
}
