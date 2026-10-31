interface Rectangle {
    int LENGTH = 10;
    int BREADTH = 20;

    default void printRectangleArea() {
        System.out.println("Rectangle area: " + LENGTH * BREADTH);
    }
}

interface Circle {
    double RADIUS = 7.77;

    default void printCircleArea() {
        System.out.printf("Circle area: %.2f%n", Math.PI * RADIUS * RADIUS);
    }
}

public class InterfaceMultipleInheritanceDemo implements Rectangle, Circle {
    public static void main(String[] args) {
        InterfaceMultipleInheritanceDemo demo = new InterfaceMultipleInheritanceDemo();
        demo.printRectangleArea();
        demo.printCircleArea();
    }
}
