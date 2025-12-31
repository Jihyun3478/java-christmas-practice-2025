package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.order.Calendar;
import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import java.util.EnumMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");

        Calendar calendar = getCalendar();

        Order orders = getOrder();
        System.out.println("\n<주문 메뉴>");
        for (Map.Entry<Menu, Integer> order : orders.getOrders().entrySet()) {
            Menu orderMenu = order.getKey();
            Integer orderCount = order.getValue();

            System.out.println(orderMenu.getName() + " " + orderCount + "개");
        }
    }

    private static Calendar getCalendar() {
        while (true) {
            try {
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                String input = Console.readLine();

                int visitDay = InputParser.parseVisitDay(input);
                return new Calendar(visitDay);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private static Order getOrder() {
        while (true) {
            try {
                System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

                String input = Console.readLine();
                EnumMap<Menu, Integer> orders = InputParser.parseOrder(input);
                return new Order(orders);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
