package org.example.infrastructure;

import org.example.domain.IInventoryRepository;
import org.example.domain.IProductRepository;
import org.example.domain.Inventory;
import org.example.domain.Product;

import java.time.LocalDateTime;
import java.util.*;

public class InMemoryInventoryRepository implements IInventoryRepository {
    private final Map<Integer, Inventory> inventories = new HashMap<>();

    @Override
    public void addInventory(Inventory inventory, Product product) {
        if (product.isDamaged()) {
            throw new IllegalArgumentException("Продукт списан, так как поврежден или просрочен!");
        }
        if (product.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Продукт просрочен!");
        }
        if (inventories.containsKey(inventory.getProductId())) {
            throw new IllegalArgumentException("Инвентаризация с таким продуктом уже существует!");
        }
        inventories.put(inventory.getProductId(), inventory);
    }

    @Override
    public void writeOffProductsDueUsed(int productId, int count) {
        if (!inventories.containsKey(productId)) {
            throw new IllegalArgumentException("Такого продукта нет");
        }

        Inventory inventory = findById(productId).get();

        if (count > inventory.getQuantity()) {
            throw new IllegalArgumentException("Нельзя списать больше, чем есть");
        }

        if (count == inventory.getQuantity()) {
            inventories.remove(productId);
        }

        inventory.setQuantity(inventory.getQuantity() - count);
        inventories.put(productId, inventory);
    }

    public void removeInventory(Inventory inventory) {
        if (!inventories.containsKey(inventory.getProductId())) {
            throw new IllegalArgumentException("Такого продукта нет");
        }
        inventories.remove(inventory.getProductId());
        System.out.println("Продукт успешно списан!");
    }

    @Override
    public void trackProductsWithCriticalLevel() {
        for (Inventory inventory: inventories.values()) {
            String result = inventory.getQuantity() < inventory.getCriticalLevel() ? "критический" : "хороший";
            System.out.println("Инвентаризация с ID: " + inventory.getProductId() + " имеет " + result + " уровень запаса!");
        }
    }

    @Override
    public void updateRealStock(Inventory inventory, int stock) {
        inventory.setQuantity(stock);
        inventories.put(inventory.getProductId(), inventory);
    }

    @Override
    public Optional<Inventory> findById(int id) {
        return Optional.ofNullable(inventories.get(id));
    }

    @Override
    public List<Inventory> findAllInventories() {
        return new ArrayList<>(inventories.values());
    }
}
