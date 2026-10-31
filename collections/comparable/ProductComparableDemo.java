import java.util.TreeSet;

public class ProductComparableDemo {
    public static void main(String[] args) {
        TreeSet<Product> products = new TreeSet<>();
        products.add(new Product("Laptop", 100099));
        products.add(new Product("Smartphone", 70549));
        products.add(new Product("Tablet", 71199));
        products.add(new Product("Headphones", 599));
        products.add(new Product("Smartwatch", 2599));
        products.add(new Product("Laptop", 100099)); // Duplicate: not added

        System.out.println("Products sorted by price:");
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
