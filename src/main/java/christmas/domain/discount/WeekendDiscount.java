package christmas.domain.discount;

import christmas.domain.order.Calendar;
import christmas.domain.order.MenuType;
import christmas.domain.order.Order;

public class WeekendDiscount implements Discount {
    private static final int WEEKEND_DISCOUNT_AMOUNT = 2023;

    @Override
    public boolean isAvailable(Order order, Calendar calendar) {
        if (!calendar.isWeekend(calendar.visitDay())) {
            return false;
        }
        return order.getMenuCountByType(MenuType.MAIN) > 0;
    }

    @Override
    public int calculate(Order order, Calendar calendar) {
        int mainMenuCount = order.getMenuCountByType(MenuType.MAIN);
        return mainMenuCount * WEEKEND_DISCOUNT_AMOUNT;
    }

    @Override
    public String getName() {
        return DiscountType.WEEKEND.getName();
    }
}
