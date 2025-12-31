package christmas.domain.discount;

import christmas.domain.order.Calendar;
import christmas.domain.order.Order;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DiscountGenerator {
    private final List<Discount> discounts;

    public DiscountGenerator() {
        this.discounts = List.of(
                new ChristmasDiscount(),
                new WeekdaysDiscount(),
                new WeekendDiscount(),
                new SpecialDiscount()
        );
    }

    public List<Discount> getAvailableDiscount(Order order, Calendar calendar) {
        return discounts.stream()
                .filter(discount -> discount.isAvailable(order, calendar))
                .toList();
    }

    public int calculateTotalDiscount(Order order, Calendar calendar) {
        int totalAmount = order.calculateTotalAmount();
        if (totalAmount < 10000) {
            return 0;
        }

        return discounts.stream()
                .filter(discount -> discount.isAvailable(order, calendar))
                .mapToInt(discount -> discount.calculate(order, calendar))
                .sum();
    }

    public Map<String, Integer> getDiscountDetails(Order order, Calendar calendar) {
        return discounts.stream()
                .filter(discount -> discount.isAvailable(order, calendar))
                .collect(Collectors.toMap(
                        Discount::getName,
                        discount -> discount.calculate(order, calendar)
                ));
    }
}
