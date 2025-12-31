package christmas.domain.discount;

import christmas.domain.order.Calendar;
import christmas.domain.order.Order;

public interface Discount {
    boolean isAvailable(Order order, Calendar calendar);
    int calculate(Order order, Calendar calendar);
    String getName();
}
