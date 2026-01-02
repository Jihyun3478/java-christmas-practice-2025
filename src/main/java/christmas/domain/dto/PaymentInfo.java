package christmas.domain.dto;

public record PaymentInfo(
        int totalAmount,
        int finalAmount
) {
    public static PaymentInfo from(int totalAmount, int finalAmount) {
        return new PaymentInfo(totalAmount, finalAmount);
    }
}
