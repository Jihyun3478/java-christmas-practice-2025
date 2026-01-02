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

    public void printTotalOrderAmount(int totalAmount) {
        System.out.println(LINE_SEPARATOR + "<할인 전 총주문 금액>");
        System.out.printf("%,d원%n", totalAmount);
    }

    public void printPresentEvent(boolean hasPresent) {
        System.out.println(LINE_SEPARATOR + "<증정 메뉴>");

        if (hasPresent) {
            System.out.println("샴페인 1개");
        }
        if (!hasPresent) {
            System.out.println("없음");
        }
    }

    public void printBenefitDetails(Map<String, Integer> benefits) {
        System.out.println(LINE_SEPARATOR + "<혜택 내역>");
        
        if (benefits.isEmpty()) {
            System.out.println("없음");
        }
        if (!benefits.isEmpty()) {
            benefits.forEach((name, amount) ->
                    System.out.printf("%s: -%,d원%n", name, amount)
            );
        }
    }

    public void printTotalBenefitAmount(int totalBenefit) {
        System.out.println(LINE_SEPARATOR + "<총혜택 금액>");

        if (totalBenefit == 0) {
            System.out.printf("%,d원%n", totalBenefit);
        }
        if (totalBenefit != 0) {
            System.out.printf("-%,d원%n", totalBenefit);
        }
    }

    public void printFinalAmount(int finalAmount) {
        System.out.println(LINE_SEPARATOR + "<할인 후 예상 결제 금액>");
        System.out.printf("%,d원%n", finalAmount);
    }

    public void printBadge(String badgeName) {
        System.out.println(LINE_SEPARATOR + "<12월 이벤트 배지>");
        System.out.println(badgeName);
    }

    public void printErrorMessage(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }
}
