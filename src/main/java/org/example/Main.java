package org.example;

import org.example.application.ControlProductsService;
import org.example.domain.IInventoryRepository;
import org.example.domain.IProductRepository;
import org.example.domain.Inventory;
import org.example.domain.Product;
import org.example.infrastructure.InMemoryInventoryRepository;
import org.example.infrastructure.InMemoryProductRepository;
import org.example.presentation.ControlProductsConsoleUI;

public class Main {
    public static void main(String[] args) {
        // Создание инфраструктурного слоя (репозитории)
        IProductRepository productRepository = new InMemoryProductRepository();
        IInventoryRepository inventoryRepository = new InMemoryInventoryRepository();

        // Создание слоя приложения (сервис)
        ControlProductsService controlProductsService = new ControlProductsService(inventoryRepository, productRepository);

        // Добавление тестовых данных
        Inventory inventory = new Inventory(0, 100, 50, 150);
        Product product = new Product(0, "Вода");
        controlProductsService.addProduct(inventory, product);


        // Создание слоя представления (UI)
        ControlProductsConsoleUI ui = new ControlProductsConsoleUI(controlProductsService, inventoryRepository);

        // Запуск приложения
        ui.start();
    }
}