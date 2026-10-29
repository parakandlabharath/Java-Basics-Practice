public class SwitchExpressionDemo {
    public static void main(String[] args) {
        int choice = 2;
        String str = switch (choice) {
            case 1 -> "case One";
            case 2 -> "case Two";
            case 3 -> "case Three";
            default -> "Default case";
        };
        System.out.println(str);
    }
}