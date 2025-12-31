package christmas.domain.discount;

import christmas.domain.order.Calendar;
import christmas.domain.order.MenuType;
import christmas.domain.order.Order;

public class WeekendDiscount implements Discount {
    private static final int DISCOUNT_AMOUNT = 2023;

    @Override
    public boolean isAvailable(Order order, Calendar calendar) {
        return !calendar.isWeekend(calendar.visitDay());
    }

    @Override
    public int calculate(Order order, Calendar calendar) {
        int mainMenuCount = order.getMenuCountByType(MenuType.MAIN);
        return mainMenuCount * DISCOUNT_AMOUNT;
    }

    @Override
    public String getName() {
        return DiscountType.WEEKEND.getName();
    }
}
