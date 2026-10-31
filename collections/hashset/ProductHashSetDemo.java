public class ProductHashSetDemo {
    public static void main(String[] args) {
        ProductOperation operations = new ProductOperation();

        operations.addProduct(new Product(1, "Laptop", 1000.0));
        operations.addProduct(new Product(2, "Phone", 500.0));
        operations.addProduct(new Product(3, "Tablet", 300.0));

        System.out.println("Added products:");
        operations.getProducts().forEach(System.out::println);

        boolean updated = operations.updateProductPrice(2, 450.0);
        System.out.println("Phone price updated: " + updated);
        operations.getProducts().forEach(System.out::println);
    }
}
