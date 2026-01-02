package christmas.domain.dto;

import java.util.Map;

public record BenefitInfo(
        Map<String, Integer> benefitDetails,
        int totalBenefitAmount
) {
    public static BenefitInfo from(Map<String, Integer> benefitDetails, int totalBenefitAmount) {
        return new BenefitInfo(benefitDetails, totalBenefitAmount);
    }
}
