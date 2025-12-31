package christmas.domain.order;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;

public class Order {
    private final EnumMap<Menu, Integer> orders;

    public Order(EnumMap<Menu, Integer> orders) {
        validate(orders);
        this.orders = orders;
    }

    private void validate(EnumMap<Menu, Integer> orders) {
        validateTotalCount(orders);
        validateOnlyDrink(orders);
    }

    private void validateOnlyDrink(EnumMap<Menu, Integer> orders) {
        boolean allDrink = orders.keySet().stream()
                .allMatch(Menu::isDrink);

        if (allDrink) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private void validateTotalCount(EnumMap<Menu, Integer> orders) {
        int totalOrderCount = getTotalOrderCount(orders);
        if (totalOrderCount < 1 || totalOrderCount > 20) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public int getTotalOrderCount(EnumMap<Menu, Integer> orders) {
        int totalOrderCount = 0;
        for (int orderCount : orders.values()) {
            totalOrderCount += orderCount;
        }
        return totalOrderCount;
    }

    public int calculateTotalAmount() {
        int totalAmount = 0;
        for (Entry<Menu, Integer> order : orders.entrySet()) {
            totalAmount += order.getKey().getPrice() * order.getValue();
        }
        return totalAmount;
    }

    public Map<Menu, Integer> getOrders() {
        return Collections.unmodifiableMap(orders);
    }
}
