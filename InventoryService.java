import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryService {
    private Map<Integer, Product> products = new HashMap<>();

    public void addProduct(Product product) {
        if (products.containsKey(product.getProductId())) {
            throw new IllegalArgumentException("Product ID " + product.getProductId() + " already exists.");
        }
        products.put(product.getProductId(), product);
    }

    public Product searchById(int id) {
        Product p = products.get(id);
        if (p == null) {
            throw new IllegalArgumentException("No product found with ID " + id);
        }
        return p;
    }

    public void updatePrice(int id, double newPrice) {
        searchById(id).setPrice(newPrice);
    }

    public void addStock(int id, int quantity) {
        searchById(id).addStock(quantity);
    }

    // Sells the product and adds it to the bill
    public void sellProduct(int id, int quantity, Bill bill) {
        Product p = searchById(id);
        p.sellProduct(quantity);
        bill.addItem(p, quantity);
    }

    public Collection<Product> getAllProducts() {
        return products.values();
    }

    public List<Product> getLowStockProducts() {
        List<Product> low = new ArrayList<>();
        for (Product p : products.values()) {
            if (p.getStock() < 5) {
                low.add(p);
            }
        }
        return low;
    }

    public void deleteProduct(int id) {
        searchById(id);
        products.remove(id);
    }
}
