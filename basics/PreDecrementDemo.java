import java.util.Scanner;

public class PreDecrementDemo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a: ");
            int a = scanner.nextInt();

            System.out.print("Enter b: ");
            int b = scanner.nextInt();

            int sum = --a + --b;

            System.out.println("a after pre-decrement: " + a);
            System.out.println("b after pre-decrement: " + b);
            System.out.println("Sum: " + sum);
        }
    }
}
