public class StarSquarePattern {
    public static void main(String[] args) {
        int size = 3;

        for (int row = 1; row <= size; row++) {
            for (int column = 1; column <= size; column++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
