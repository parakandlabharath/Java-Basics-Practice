import java.util.ArrayList;
import java.util.Collections;

public class ArrayListSortDemo {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("Bharath");
        names.add("Parakandla");
        Collections.sort(names);
        System.out.println(names);
    }
}