package christmas.service;

import christmas.domain.discount.DiscountGenerator;
import christmas.domain.dto.BenefitInfo;
import christmas.domain.dto.PaymentInfo;
import christmas.domain.event.BadgeEvent;
import christmas.domain.event.PresentationEvent;
import christmas.domain.order.Calendar;
import christmas.domain.order.Order;
import java.util.LinkedHashMap;
import java.util.Map;

public class EventService {
    private final DiscountGenerator discountGenerator;
    private final PresentationEvent presentationEvent;

    public EventService(DiscountGenerator discountGenerator, PresentationEvent presentationEvent) {
        this.discountGenerator = discountGenerator;
        this.presentationEvent = presentationEvent;
    }

    public boolean hasPresentEvent(int totalAmount) {
        return presentationEvent.isAvailable(totalAmount);
    }

    public BenefitInfo calculateBenefitInfo(Order order, Calendar calendar) {
        Map<String, Integer> benefitDetails = calculateBenefitDetails(order, calendar);
        int totalBenefitAmount = calculateTotalBenefitAmount(order, calendar);

        return BenefitInfo.from(benefitDetails, totalBenefitAmount);
    }

    public PaymentInfo calculatePaymentInfo(Order order, Calendar calendar) {
        int totalAmount = order.calculateTotalAmount();
        int totalDiscount = discountGenerator.calculateTotalDiscount(order, calendar);
        int finalAmount = totalAmount - totalDiscount;

        return PaymentInfo.from(totalAmount, finalAmount);
    }

    public BadgeEvent calculateBadge(int totalBenefitAmount) {
        return BadgeEvent.getBadgeByTotalBenefit(totalBenefitAmount);
    }

    private Map<String, Integer> calculateBenefitDetails(Order order, Calendar calendar) {
        Map<String, Integer> benefits = new LinkedHashMap<>(
                discountGenerator.getDiscountDetails(order, calendar)
        );

        int totalAmount = order.calculateTotalAmount();
        if (presentationEvent.isAvailable(totalAmount)) {
            benefits.put("증정 이벤트", presentationEvent.getPresentAmount());
        }
        return benefits;
    }

    private int calculateTotalBenefitAmount(Order order, Calendar calendar) {
        int totalAmount = order.calculateTotalAmount();
        int totalDiscount = discountGenerator.calculateTotalDiscount(order, calendar);

        int presentAmount = 0;
        if (presentationEvent.isAvailable(totalAmount)) {
            presentAmount = presentationEvent.getPresentAmount();
        }
        return totalDiscount + presentAmount;
    }
}
