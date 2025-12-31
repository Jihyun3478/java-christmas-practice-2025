package christmas.view;

import christmas.domain.order.Menu;
import java.util.Map;

public class OutputView {
    private static final String LINE_SEPARATOR = "\n";

    public void printStartMessage() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public void printIntro(int visitDay) {
        System.out.println("12월 " + visitDay + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
    }

    public void printMenu(Map<Menu, Integer> orders) {
        System.out.println(LINE_SEPARATOR + "<주문 메뉴>");

        for (Map.Entry<Menu, Integer> order : orders.entrySet()) {
            Menu orderMenu = order.getKey();
            Integer orderCount = order.getValue();

            System.out.println(orderMenu.getName() + " " + orderCount + "개");
        }
    }
}
