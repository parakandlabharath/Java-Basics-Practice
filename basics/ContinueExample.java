import java.util.Scanner;

class ContinueExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        int i = 0;
        while (i < n) {
            i++;
            if (i % 2 == 0) {
                continue;
            }
            System.out.println(i);
        }
        scanner.close();
    }
}