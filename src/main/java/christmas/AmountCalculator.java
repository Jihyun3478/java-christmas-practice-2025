package christmas;

import christmas.domain.order.Order;

public class AmountCalculator {
    public int getTotalAmount(Order order) {
        return order.calculateTotalAmount();
    }
}
