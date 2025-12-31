package christmas.domain.discount;

import christmas.domain.order.Calendar;
import christmas.domain.order.MenuType;
import christmas.domain.order.Order;

public class WeekdaysDiscount implements Discount {
    private static final int WEEKDAY_DISCOUNT_AMOUNT = 2023;

    @Override
    public boolean isAvailable(Order order, Calendar calendar) {
        if (calendar.isWeekend(calendar.visitDay())) {
            return false;
        }
        return order.getMenuCountByType(MenuType.DESSERT) > 0;
    }

    @Override
    public int calculate(Order order, Calendar calendar) {
        int dessertMenuCount = order.getMenuCountByType(MenuType.DESSERT);
        return dessertMenuCount * WEEKDAY_DISCOUNT_AMOUNT;
    }

    @Override
    public String getName() {
        return DiscountType.WEEKDAYS.getName();
    }
}
