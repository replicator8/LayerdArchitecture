package org.example.application;

import org.example.domain.IInventoryRepository;
import org.example.domain.IProductRepository;
import org.example.domain.Inventory;
import org.example.domain.Product;
import java.time.LocalDateTime;

public class ControlProductsService {
    private final IInventoryRepository inventoryRepository;
    private final IProductRepository productRepository;

    public ControlProductsService(IInventoryRepository inventoryRepository, IProductRepository productRepository) {
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
    }

    public void addProduct(Inventory inventory, Product product) {
        inventoryRepository.addInventory(inventory, product);
        productRepository.addProduct(product);
        System.out.println("Продукт добавлен в инвентарь!");
    }

    public void writeOffProductsDueUsed(int productId, int count) {
        inventoryRepository.writeOffProductsDueUsed(productId, count);
        System.out.println("Часть продукта списана из-за использования!");
    }

    public void writeOffExpiredProducts(Inventory inventory) {
        Product product = productRepository.findById(inventory.getProductId()).get();
        if (product.getExpirationDate().isBefore(LocalDateTime.now())) {
            inventoryRepository.removeInventory(inventory);
        } else {
            product.setExpirationDate(LocalDateTime.now().minusDays(1));
            productRepository.addProduct(product);
            inventoryRepository.removeInventory(inventory);
//            System.out.println("Продукт теперь просрочен!");
        }
    }

    public void generateReport(Inventory inventory) {
        Product product = productRepository.findById(inventory.getProductId()).get();

        System.out.println(inventory.getCurrentStocks(product.getName()));
    }

    public void trackProductsWithCriticalLevel() {
        inventoryRepository.trackProductsWithCriticalLevel();
    }

    public void makeInventory(Inventory inventory, int realQuantity) {
        if (realQuantity < 0) {
            throw new IllegalArgumentException("Отрицательного количества быть не может!");
        }
        inventoryRepository.updateRealStock(inventory, realQuantity);
        System.out.println("Инвентаризация успешно проведена!");
    }
}
