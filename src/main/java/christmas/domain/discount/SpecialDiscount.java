package christmas.domain.discount;

import christmas.domain.order.Calendar;
import christmas.domain.order.Order;

public class SpecialDiscount implements Discount {
    private static final int SPECIAL_DISCOUNT_AMOUNT = 1000;

    @Override
    public boolean isAvailable(Order order, Calendar calendar) {
        return calendar.isSpecial(calendar.visitDay());
    }

    @Override
    public int calculate(Order order, Calendar calendar) {
        return SPECIAL_DISCOUNT_AMOUNT;
    }

    @Override
    public String getName() {
        return DiscountType.SPECIAL.getName();
    }
}
