package christmas;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        Calendar calendar = getCalendar();
    }

    private static Calendar getCalendar() {
        while (true) {
            try {
                int visitDay = Integer.parseInt(Console.readLine());
                return new Calendar(visitDay);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
