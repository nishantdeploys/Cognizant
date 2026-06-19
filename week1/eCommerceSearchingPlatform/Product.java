/**
 * Product class for e-commerce platform
 * Implements Comparable interface for sorting by productId (required for binary search)
 */
public class Product implements Comparable<Product> {
    private int productId;
    private String productName;
    private String category;
    private int quantity;
    private double price;

    public Product(int productId, String productName, String category, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(Product other) {
        return Integer.compare(this.productId, other.productId);
    }

    @Override
    public String toString() {
        return "Product [ID=" + productId + ", Name=" + productName + 
               ", Category=" + category + ", Qty=" + quantity + 
               ", Price=$" + String.format("%.2f", price) + "]";
    }
}
