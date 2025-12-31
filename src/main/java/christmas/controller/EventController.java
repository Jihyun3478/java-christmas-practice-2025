package christmas.controller;

import camp.nextstep.edu.missionutils.Console;
import christmas.AmountCalculator;
import christmas.util.InputParser;
import christmas.domain.event.BadgeEvent;
import christmas.domain.event.PresentationEvent;
import christmas.domain.order.Calendar;
import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.EnumMap;
import java.util.Map;

public class EventController {
    private final InputView inputView;
    private final OutputView outputView;

    public EventController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        outputView.printStartMessage();

        Calendar calendar = getCalendar();
        Order orders = getOrder();

        outputView.printIntro(calendar.visitDay());
        outputView.printMenu(orders.getOrders());

        AmountCalculator calculator = new AmountCalculator();
        int totalAmount = calculator.getTotalAmount(orders);

        System.out.println("\n<할인 전 총주문 금액>");
        System.out.printf("%,d원%n", totalAmount);

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
                    System.out.printf("%s: -%,d원%n", name, amount)
            );
        }

        System.out.println("\n<총혜택 금액>");
        int totalBenefit = calculator.getTotalBenefit(orders, calendar);
        if (totalBenefit == 0) {
            System.out.printf("%,d원%n", totalBenefit);
        }
        if (totalBenefit != 0) {
            System.out.printf("-%,d원%n", totalBenefit);
        }

        System.out.println("\n<할인 후 예상 결제 금액>");
        int finalAmount = calculator.getFinalAmount(orders, calendar);
        System.out.printf("%,d원%n", finalAmount);

        System.out.println("\n<12월 이벤트 배지>");
        BadgeEvent badge = BadgeEvent.getBadgeByTotalBenefit(totalBenefit);
        System.out.println(badge.getName());
    }

    private Calendar getCalendar() {
        while (true) {
            try {
                String input = inputView.readDate();
                int visitDay = InputParser.parseVisitDay(input);

                return new Calendar(visitDay);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private Order getOrder() {
        while (true) {
            try {
                String input = inputView.readOrders();
                EnumMap<Menu, Integer> orders = InputParser.parseOrder(input);

                return new Order(orders);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
