public class SwitchEnumDemo {
    enum Day {
        MONDAY, TUESDAY, FRIDAY
    }

    public static void main(String[] args) {
        Day selectedDay = Day.MONDAY;

        switch (selectedDay) {
            case MONDAY -> System.out.println("Today is Monday.");
            case TUESDAY -> System.out.println("Today is Tuesday.");
            case FRIDAY -> System.out.println("Today is Friday.");
        }
    }
}
