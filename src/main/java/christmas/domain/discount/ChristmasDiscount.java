package christmas.domain.discount;

import christmas.domain.order.Calendar;
import christmas.domain.order.Order;

public class ChristmasDiscount implements Discount {
    private static final int START_DAY = 1;
    private static final int FINAL_DAY = 25;
    private static final int START_AMOUNT = 1000;
    private static final int INCREASE_AMOUNT = 100;

    @Override
    public boolean isAvailable(Order order, Calendar calendar) {
        int visitDay = calendar.visitDay();
        return visitDay >= START_DAY && visitDay <= FINAL_DAY;
    }

    @Override
    public int calculate(Order order, Calendar calendar) {
        int visitDay = calendar.visitDay();
        return START_AMOUNT + ((visitDay - 1) * INCREASE_AMOUNT);
    }

    @Override
    public String getName() {
        return DiscountType.CHRISTMAS_D_DAY.getName();
    }
}
