import java.util.Scanner;

public class AmicableNos {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter two positive integers: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter valid whole numbers.");
                return;
            }
            int first = scanner.nextInt();

            if (!scanner.hasNextInt()) {
                System.out.println("Please enter two valid whole numbers.");
                return;
            }
            int second = scanner.nextInt();

            if (first <= 0 || second <= 0) {
                System.out.println("Both numbers must be positive.");
                return;
            }

            long firstDivisorSum = sumOfProperDivisors(first);
            long secondDivisorSum = sumOfProperDivisors(second);

            if (first != second && firstDivisorSum == second && secondDivisorSum == first) {
                System.out.println(first + " and " + second + " are amicable numbers.");
            } else {
                System.out.println(first + " and " + second + " are not amicable numbers.");
            }
        }
    }

    private static long sumOfProperDivisors(int number) {
        if (number == 1) {
            return 0;
        }

        long sum = 1;
        for (int divisor = 2; divisor <= number / divisor; divisor++) {
            if (number % divisor == 0) {
                sum += divisor;
                int pairedDivisor = number / divisor;
                if (pairedDivisor != divisor) {
                    sum += pairedDivisor;
                }
            }
        }
        return sum;
    }
}
