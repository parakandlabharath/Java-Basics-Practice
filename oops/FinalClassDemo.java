class FinalParent {
    private final int age = 77;

    public int getAge() {
        return age;
    }

    public void display() {
        System.out.println("Parent age: " + age);
    }
}

public class FinalClassDemo extends FinalParent {
    private int age = 88;

    @Override
    public void display() {
        System.out.println("Child age: " + age);
        System.out.println("Parent age through getter: " + getAge());
    }

    public static void main(String[] args) {
        FinalClassDemo child = new FinalClassDemo();
        child.display();

        child.age = 99;
        System.out.println("Updated child age: " + child.age);
    }
}
