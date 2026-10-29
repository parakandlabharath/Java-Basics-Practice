import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value of ch1");
        char ch1 = sc.next().charAt(0);
        char ch2 = ch1++;
        System.out.println("the value of ch1 after post increment is " + ch1);
        System.out.println("the value of ch2 after post increment is " + ch2);
        sc.close();
    }
}