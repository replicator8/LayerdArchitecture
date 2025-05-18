package org.example.presentation;

import org.example.application.ControlProductsService;
import org.example.domain.IInventoryRepository;
import org.example.domain.Inventory;
import org.example.domain.Product;
import java.util.Scanner;

public class ControlProductsConsoleUI {
    private final ControlProductsService controlProductsService;
    private final Scanner scanner;
    private final IInventoryRepository inventoryRepository;

    public ControlProductsConsoleUI(ControlProductsService controlProductsService, IInventoryRepository inventoryRepository) {
        this.controlProductsService = controlProductsService;
        this.scanner = new Scanner(System.in);
        this.inventoryRepository = inventoryRepository;
    }

    public void start() {
        int choice;
        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // очистка буфера
            handleMenuChoice(choice);
        } while (choice != 0);
    }

    private void showMenu() {
        System.out.println("\n===== Cистема управления инвентаризацией ресторана =====");
        System.out.println("1. Добавить продукт в инвентаризацию");
        System.out.println("2. Списать продукты при использовании для приготовления блюд");
        System.out.println("3. Списать просроченные продукты");
        System.out.println("4. Генерация отчетов о текущих запасах");
        System.out.println("5. Провести инвентаризацию");
        System.out.println("6. Получить список продуктов с критическим уровнем запаса");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    private void handleMenuChoice(int choice) {
        switch (choice) {
            case 1:
                addProduct();
                break;
            case 2:
                writeOffProductsDueUsed();
                break;
            case 3:
                writeOffExpiredProducts();
                break;
            case 4:
                generateReport();
                break;
            case 5:
                startInventory();
                break;
            case 6:
                getCriticalLevelProducts();
            case 0:
                System.out.println("Выход из программы...");
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }

    private void addProduct() {
        System.out.print("Введите ID продукта: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите название продукта: ");
        String productName = scanner.nextLine();
        System.out.print("Введите минимальный запас продукта: ");
        int minStock = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите оптимальный запас продукта: ");
        int optimalStock = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите критический уровень продукта: ");
        int criticalLevel = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите количество продукта: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.println("");

        try {
            controlProductsService.addProduct(new Inventory(id, minStock, optimalStock, criticalLevel, quantity), new Product(id, productName));
            System.out.println("");
        } catch (Exception e) {
            System.out.println("Ошибка при создании продукта: " + e.getMessage());
        }
    }

    public void writeOffProductsDueUsed() {
        System.out.print("Введите ID продукта: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите количество продуктов, которые использовали: ");
        int count = Integer.parseInt(scanner.nextLine());
        System.out.println("");

        try {
            controlProductsService.writeOffProductsDueUsed(id, count);
        } catch (Exception e) {
            System.out.println("Ошибка при списании продуктов: " + e.getMessage());
        }
    }

    public void writeOffExpiredProducts() {
        System.out.print("Введите ID продукта: ");
        int id = Integer.parseInt(scanner.nextLine());
        Inventory inventory = inventoryRepository.findById(id).get();
        System.out.println("");

        try {
            controlProductsService.writeOffExpiredProducts(inventory);
        } catch (Exception e) {
            System.out.println("Ошибка при списании продуктов: " + e.getMessage());
        }
    }

    public void generateReport() {
        System.out.print("Введите ID инвентаризации: ");
        int id = Integer.parseInt(scanner.nextLine());
        Inventory inventory = inventoryRepository.findById(id).get();

        try {
            controlProductsService.generateReport(inventory);
        } catch (Exception e) {
            System.out.println("Ошибка при генерации отчета: " + e.getMessage());
        }
    }

    public void startInventory() {
        System.out.print("Введите ID продукта: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Введите количество продукта, найденного на складе: ");
        int stock = Integer.parseInt(scanner.nextLine());
        Inventory inventory = inventoryRepository.findById(id).get();
        System.out.println("");

        try {
            controlProductsService.makeInventory(inventory, stock);
        } catch (Exception e) {
            System.out.println("Ошибка при проведении инвентаризации: " + e.getMessage());
        }
    }

    public void getCriticalLevelProducts() {
        System.out.print("Введите ID продукта: ");
        int id = Integer.parseInt(scanner.nextLine());
        Inventory inventory = inventoryRepository.findById(id).get();
        System.out.println("");

        try {
            controlProductsService.trackProductsWithCriticalLevel(inventory);
        } catch (Exception e) {
            System.out.println("Ошибка при получении критического уровня продуктов: " + e.getMessage());
        }
    }
}
