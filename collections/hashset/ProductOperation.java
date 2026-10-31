import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class ProductOperation {
    private final Set<Product> products = new HashSet<>();

    public boolean addProduct(Product product) {
        return products.add(product);
    }

    public boolean updateProductPrice(int productId, double newPrice) {
        for (Product product : products) {
            if (product.getId() == productId) {
                product.setPrice(newPrice);
                return true;
            }
        }
        return false;
    }

    public Set<Product> getProducts() {
        return Collections.unmodifiableSet(products);
    }
}
