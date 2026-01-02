package christmas.controller;

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
import java.util.function.Supplier;

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

        outputView.printTotalOrderAmount(totalAmount);

        PresentationEvent presentationEvent = new PresentationEvent();
        outputView.printPresentEvent(presentationEvent, totalAmount);


        Map<String, Integer> benefits = calculator.getBenefitDetails(orders, calendar);
        outputView.printBenefitDetails(benefits);

        int totalBenefit = calculator.getTotalBenefit(orders, calendar);
        outputView.printTotalBenefitAmount(totalBenefit);

        int finalAmount = calculator.getFinalAmount(orders, calendar);
        outputView.printFinalAmount(finalAmount);

        BadgeEvent badge = BadgeEvent.getBadgeByTotalBenefit(totalBenefit);
        outputView.printBadge(badge.getName());
    }

    private Calendar getCalendar() {
        return retryUntilSuccess(() -> {
            String input = inputView.readDate();
            int visitDay = InputParser.parseVisitDay(input);

            return new Calendar(visitDay);
        });
    }

    private Order getOrder() {
        return retryUntilSuccess(() -> {
            String input = inputView.readOrders();
            EnumMap<Menu, Integer> orders = InputParser.parseOrder(input);

            return new Order(orders);
        });
    }

    private <T> T retryUntilSuccess(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e);
            }
        }
    }
}
