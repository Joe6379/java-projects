import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final InventoryService service = new InventoryService();

    public static void main(String[] args) {
        // sample data so the shop is not empty on first run
        service.addProduct(new Product(1, "Dell Laptop", Category.LAPTOP, 55000, 10));
        service.addProduct(new Product(2, "iPhone", Category.MOBILE, 70000, 3));
        service.addProduct(new Product(3, "Keyboard", Category.ACCESSORY, 800, 20));
        service.addProduct(new Product(4, "Mixer Grinder", Category.HOME_APPLIANCE, 3500, 0));

        while (true) {
            printMenu();
            int choice = readInt("Enter choice: ");
            try {
                switch (choice) {
                    case 1: addProduct(); break;
                    case 2: searchProduct(); break;
                    case 3: updatePrice(); break;
                    case 4: addStock(); break;
                    case 5: sellProducts(); break;
                    case 6: showAll(); break;
                    case 7: showLowStock(); break;
                    case 8: deleteProduct(); break;
                    case 0:
                        System.out.println("Goodbye!");
                        return;
                    default: System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== Inventory Management System =====");
        System.out.println("1. Add a product");
        System.out.println("2. Search product by ID");
        System.out.println("3. Update product price");
        System.out.println("4. Add stock");
        System.out.println("5. Sell product (generate bill)");
        System.out.println("6. Show all products");
        System.out.println("7. Show products with stock below 5");
        System.out.println("8. Delete a product");
        System.out.println("0. Exit");
    }

    private static void addProduct() {
        int id = readInt("Product ID: ");
        System.out.print("Product name: ");
        String name = sc.nextLine().trim();
        System.out.println("Category: 1=LAPTOP 2=MOBILE 3=ACCESSORY 4=HOME_APPLIANCE");
        int c = readInt("Choose category: ");
        if (c < 1 || c > 4) {
            throw new IllegalArgumentException("Invalid category.");
        }
        Category category = Category.values()[c - 1];
        double price = readDouble("Price: ");
        int stock = readInt("Initial stock: ");
        service.addProduct(new Product(id, name, category, price, stock));
        System.out.println("Product added.");
    }

    private static void searchProduct() {
        System.out.println(service.searchById(readInt("Product ID: ")));
    }

    private static void updatePrice() {
        int id = readInt("Product ID: ");
        service.updatePrice(id, readDouble("New price: "));
        System.out.println("Price updated.");
    }

    private static void addStock() {
        int id = readInt("Product ID: ");
        service.addStock(id, readInt("Quantity to add: "));
        System.out.println("Stock updated.");
    }

    private static void sellProducts() {
        Bill bill = new Bill();
        while (true) {
            int id = readInt("Product ID to sell (0 to finish): ");
            if (id == 0) break;
            int qty = readInt("Quantity: ");
            try {
                service.sellProduct(id, qty, bill);
                System.out.println("Added to bill.");
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        if (bill.isEmpty()) {
            System.out.println("Nothing sold.");
        } else {
            bill.print();
        }
    }

    private static void showAll() {
        if (service.getAllProducts().isEmpty()) {
            System.out.println("No products.");
        }
        for (Product p : service.getAllProducts()) {
            System.out.println(p);
        }
    }

    private static void showLowStock() {
        List<Product> low = service.getLowStockProducts();
        if (low.isEmpty()) {
            System.out.println("All products have enough stock.");
        }
        for (Product p : low) {
            System.out.println(p);
        }
    }

    private static void deleteProduct() {
        service.deleteProduct(readInt("Product ID: "));
        System.out.println("Product deleted.");
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }
}
