package org.example.infrastructure;

import org.example.domain.IProductRepository;
import org.example.domain.Product;
import java.util.*;

public class InMemoryProductRepository implements IProductRepository {
    private final Map<Integer, Product> products = new HashMap<>();

    @Override
    public void addProduct(Product product) {
        products.put(product.getId(), product);
    }

    @Override
    public Optional<Product> findById(int id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAllProducts() {
        return new ArrayList<>(products.values());
    }
}
