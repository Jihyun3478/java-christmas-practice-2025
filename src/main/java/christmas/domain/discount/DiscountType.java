package christmas.domain.discount;

public enum DiscountType {
    CHRISTMAS_D_DAY("크리스마스 디데이 할인"),
    WEEKDAYS("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
    ;

    private final String name;

    DiscountType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
