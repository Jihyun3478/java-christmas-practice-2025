package christmas.domain.discount;

import christmas.domain.order.Calendar;
import christmas.domain.order.MenuType;
import christmas.domain.order.Order;

public class WeekdaysDiscount implements Discount {
    private static final int DISCOUNT_AMOUNT = 2023;

    @Override
    public boolean isAvailable(Order order, Calendar calendar) {
        return calendar.isWeekend(calendar.visitDay());
    }

    @Override
    public int calculate(Order order, Calendar calendar) {
        int dessertMenuCount = order.getMenuCountByType(MenuType.DESSERT);
        return dessertMenuCount * DISCOUNT_AMOUNT;
    }

    @Override
    public String getName() {
        return DiscountType.WEEKDAYS.getName();
    }
}
