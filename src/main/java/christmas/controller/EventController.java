package christmas.controller;

import christmas.domain.dto.BenefitInfo;
import christmas.domain.dto.PaymentInfo;
import christmas.domain.event.BadgeEvent;
import christmas.domain.order.Calendar;
import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import christmas.service.EventService;
import christmas.util.InputParser;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.EnumMap;
import java.util.function.Supplier;

public class EventController {
    private final InputView inputView;
    private final OutputView outputView;
    private final EventService eventService;

    public EventController(InputView inputView, OutputView outputView, EventService eventService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.eventService = eventService;
    }

    public void start() {
        outputView.printStartMessage();

        Calendar calendar = getCalendar();
        Order orders = getOrder();

        printOrderInfo(calendar, orders);
        printEventResult(orders, calendar);
    }

    private void printOrderInfo(Calendar calendar, Order orders) {
        outputView.printIntro(calendar.visitDay());
        outputView.printMenu(orders.getOrders());
    }

    private void printEventResult(Order orders, Calendar calendar) {
        PaymentInfo paymentInfo = eventService.calculatePaymentInfo(orders, calendar);
        outputView.printTotalOrderAmount(paymentInfo.totalAmount());

        boolean hasPresent = eventService.hasPresentEvent(paymentInfo.totalAmount());
        outputView.printPresentEvent(hasPresent);

        BenefitInfo benefitInfo = eventService.calculateBenefitInfo(orders, calendar);
        outputView.printBenefitDetails(benefitInfo.benefitDetails());
        outputView.printTotalBenefitAmount(benefitInfo.totalBenefitAmount());

        outputView.printFinalAmount(paymentInfo.finalAmount());

        BadgeEvent badge = eventService.calculateBadge(benefitInfo.totalBenefitAmount());
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
