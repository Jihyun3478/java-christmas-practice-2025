package christmas;

import christmas.controller.EventController;
import christmas.domain.discount.DiscountGenerator;
import christmas.domain.event.PresentationEvent;
import christmas.service.EventService;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        EventController eventController = new EventController(
                new InputView(),
                new OutputView(),
                new EventService(new DiscountGenerator(), new PresentationEvent()));
        eventController.start();
    }
}
