public class StarPyramidPattern {
    public static void main(String[] args) {
        printPattern(6);
    }

    public static void printPattern(int rows) {
        for (int row = 1; row <= rows; row++) {
            for (int space = 1; space <= rows - row; space++) {
                System.out.print("  ");
            }

            for (int star = 1; star <= row * 2 - 1; star++) {
                System.out.print("* ");
            }
            System.out.println();
        }
    }
}
