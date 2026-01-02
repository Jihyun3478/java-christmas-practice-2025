package christmas.domain.event;

public enum BadgeEvent {
    NONE("없음", 0),
    STAR("별", 5_000),
    TREE("트리", 1_000),
    SANTA("산타", 20_000),
    ;

    private final String name;
    private final int minimumAmount;

    BadgeEvent(String name, int minimumAmount) {
        this.name = name;
        this.minimumAmount = minimumAmount;
    }

    public static BadgeEvent getBadgeByTotalBenefit(int totalBenefit) {
        if (totalBenefit >= SANTA.minimumAmount) {
            return SANTA;
        }
        if (totalBenefit >= TREE.minimumAmount) {
            return TREE;
        }
        if (totalBenefit >= STAR.minimumAmount) {
            return STAR;
        }

        return NONE;
    }

    public String getName() {
        return name;
    }
}
