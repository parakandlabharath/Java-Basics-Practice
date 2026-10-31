import java.util.Scanner;

public class PrintPrimes {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Print prime numbers up to: ");
            int limit = scanner.nextInt();

            if (limit < 2) {
                System.out.println("There are no prime numbers in this range.");
                return;
            }

            System.out.println("Prime numbers up to " + limit + ":");
            for (int number = 2; number <= limit; number++) {
                boolean isPrime = true;

                for (int divisor = 2; divisor <= number / divisor; divisor++) {
                    if (number % divisor == 0) {
                        isPrime = false;
                        break;
                    }
                }

                if (isPrime) {
                    System.out.print(number + " ");
                }
            }
            System.out.println();
        }
    }
}
