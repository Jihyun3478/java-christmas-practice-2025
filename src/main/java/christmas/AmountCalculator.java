package christmas;

import christmas.domain.discount.DiscountGenerator;
import christmas.domain.event.PresentationEvent;
import christmas.domain.order.Calendar;
import christmas.domain.order.Order;
import java.util.LinkedHashMap;
import java.util.Map;

public class AmountCalculator {
    private final DiscountGenerator discountGenerator;
    private final PresentationEvent presentationEvent;

    public AmountCalculator() {
        this.discountGenerator = new DiscountGenerator();
        this.presentationEvent = new PresentationEvent();
    }

    public int getTotalAmount(Order order) {
        return order.calculateTotalAmount();
    }

    public int getTotalDiscount(Order order, Calendar calendar) {
        return discountGenerator.calculateTotalDiscount(order, calendar);
    }

    public int getTotalBenefit(Order order, Calendar calendar) {
        int totalAmount = getTotalAmount(order);
        int totalDiscount = getTotalDiscount(order, calendar);

        int presentAmount = 0;
        if (presentationEvent.isAvailable(totalAmount)) {
            presentAmount = presentationEvent.getPresentAmount();
        }

        return totalDiscount + presentAmount;
    }

    public Map<String, Integer> getDiscountDetails(Order order, Calendar calendar) {
        return discountGenerator.getDiscountDetails(order, calendar);
    }

    public Map<String, Integer> getBenefitDetails(Order order, Calendar calendar) {
        Map<String, Integer> benefits = new LinkedHashMap<>(getDiscountDetails(order, calendar));

        int totalAmount = getTotalAmount(order);
        if (presentationEvent.isAvailable(totalAmount)) {
            benefits.put("증정 이벤트", presentationEvent.getPresentAmount());
        }
        return benefits;
    }

    public int getFinalAmount(Order order, Calendar calendar) {
        return getTotalAmount(order) - getTotalDiscount(order, calendar);
    }
}
