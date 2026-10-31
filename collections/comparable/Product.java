import java.util.Objects;

public class Product implements Comparable<Product> {
    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = Objects.requireNonNull(name, "name cannot be null");
        if (price < 0) {
            throw new IllegalArgumentException("price cannot be negative");
        }
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public int compareTo(Product other) {
        int priceOrder = Double.compare(this.price, other.price);
        return priceOrder != 0 ? priceOrder : this.name.compareToIgnoreCase(other.name);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Product other)) return false;
        return name.equals(other.name) && Double.compare(price, other.price) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return name + " - Rs. " + price;
    }
}
