import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsSortDemo {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Bharath");
        names.add("Parakandla");
        names.add("Java");

        Collections.sort(names);

        System.out.println("Names in alphabetical order:");
        for (String name : names) {
            System.out.println(name);
        }
    }
}
