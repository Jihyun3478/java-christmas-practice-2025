package christmas;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        Calendar calendar = getCalendar();
    }

    private static Calendar getCalendar() {
        while (true) {
            try {
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                int visitDay = Integer.parseInt(Console.readLine());
                return new Calendar(visitDay);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
