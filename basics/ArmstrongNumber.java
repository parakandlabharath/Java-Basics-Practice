import java.util.Scanner;

public class ArmstrongNumber {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter a non-negative integer: ");
            if (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid whole number.");
                return;
            }

            int number = scanner.nextInt();
            if (number < 0) {
                System.out.println("Please enter a non-negative number.");
                return;
            }

            int digits = String.valueOf(number).length();
            int remaining = number;
            int sum = 0;

            do {
                int digit = remaining % 10;
                sum += (int) Math.pow(digit, digits);
                remaining /= 10;
            } while (remaining > 0);

            if (sum == number) {
                System.out.println(number + " is an Armstrong number.");
            } else {
                System.out.println(number + " is not an Armstrong number.");
            }
        }
    }
}
