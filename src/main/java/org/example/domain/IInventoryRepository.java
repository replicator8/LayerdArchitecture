package org.example.domain;

import java.util.List;
import java.util.Optional;

public interface IInventoryRepository {
    void addInventory(Inventory inventory, Product product);
    Optional<Inventory> findById(int id);
    List<Inventory> findAllInventories();
    void writeOffProductsDueUsed(int productId, int count);
    void removeInventory(Inventory inventory);
    void trackProductsWithCriticalLevel();
    void updateRealStock(Inventory inventory, int stock);
}
