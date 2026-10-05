import java.util.ArrayList;
import java.util.List;

public class Bill {
    private static class Item {
        String name;
        int quantity;
        double price;

        Item(String name, int quantity, double price) {
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        double total() { return quantity * price; }
    }

    private List<Item> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        items.add(new Item(product.getProductName(), quantity, product.getPrice()));
    }

    public boolean isEmpty() { return items.isEmpty(); }

    public double calculateTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.total();
        }
        return total;
    }

    public void print() {
        System.out.println("\n------------- BILL -------------");
        for (Item item : items) {
            System.out.printf("%-15s %d x %.2f = %.2f%n",
                    item.name, item.quantity, item.price, item.total());
        }
        System.out.println("--------------------------------");
        System.out.printf("TOTAL: Rs.%.2f%n", calculateTotal());
    }
}
