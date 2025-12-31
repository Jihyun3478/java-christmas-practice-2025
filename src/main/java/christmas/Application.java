package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.event.PresentationEvent;
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
        System.out.println("12월 " + calendar.visitDay() + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println("\n<주문 메뉴>");
        for (Map.Entry<Menu, Integer> order : orders.getOrders().entrySet()) {
            Menu orderMenu = order.getKey();
            Integer orderCount = order.getValue();

            System.out.println(orderMenu.getName() + " " + orderCount + "개");
        }

        AmountCalculator calculator = new AmountCalculator();
        int totalAmount = calculator.getTotalAmount(orders);

        System.out.println("\n<할인 전 총주문 금액>");
        System.out.printf("%,d원\n", totalAmount);

        System.out.println("\n<증정 메뉴>");
        PresentationEvent presentationEvent = new PresentationEvent();
        if (presentationEvent.isAvailable(totalAmount)) {
            String presentName = presentationEvent.getPresentName();
            System.out.println(presentName + " 1개");
        }
        if (!presentationEvent.isAvailable(totalAmount)) {
            System.out.println("없음");
        }

        System.out.println("\n<혜택 내역>");
        Map<String, Integer> benefits = calculator.getBenefitDetails(orders, calendar);
        if (benefits.isEmpty()) {
            System.out.println("없음");
        }
        if (!benefits.isEmpty()) {
            benefits.forEach((name, amount) ->
                    System.out.println(name + ": -" + String.format("%,d", amount) + "원")
            );
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
