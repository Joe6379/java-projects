public class Product {
    private int productId;
    private String productName;
    private Category category;
    private double price;
    private int stock;        // private: can only change via addStock() / sellProduct()

    public Product(int productId, String productName, Category category, double price, int stock) {
        if (price <= 0) throw new IllegalArgumentException("Price must be greater than 0.");
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }

    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public Category getCategory() { return category; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }

    public void setPrice(double price) {
        if (price <= 0) throw new IllegalArgumentException("Price must be greater than 0.");
        this.price = price;
    }

    public void addStock(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0.");
        stock += quantity;
    }

    public void sellProduct(int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be greater than 0.");
        if (stock == 0) {
            throw new IllegalStateException(productName + " is out of stock.");
        }
        if (quantity > stock) {
            throw new IllegalStateException("Only " + stock + " unit(s) of " + productName + " left.");
        }
        stock -= quantity;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | %s | Rs.%.2f | Stock: %d",
                productId, productName, category, price, stock);
    }
}
