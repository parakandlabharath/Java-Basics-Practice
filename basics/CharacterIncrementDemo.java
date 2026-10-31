import java.util.Scanner;

public class CharacterIncrementDemo {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a character: ");
            char ch1 = scanner.next().charAt(0);

            char ch2 = ch1++;

            System.out.println("ch1 after post-increment: " + ch1);
            System.out.println("ch2 (original value): " + ch2);
        }
    }
}
