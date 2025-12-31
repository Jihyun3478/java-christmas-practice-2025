package christmas.domain.event;

import christmas.domain.order.Menu;

public class PresentationEvent {
    private static final int MINIMUM_AMOUNT = 120_000;

    public boolean isAvailable(int totalAmount) {
        return totalAmount > MINIMUM_AMOUNT;
    }

    public String getPresentName() {
        return Menu.CHAMPAGNE.getName();
    }

    public int getPresentAmount() {
        return Menu.CHAMPAGNE.getPrice();
    }
}
