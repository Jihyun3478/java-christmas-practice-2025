package christmas.domain.order;

import java.util.List;

public record Calendar(
        int visitDay
) {
    private static final int EVENT_YEAR = 2023;
    private static final int EVENT_MONTH = 12;
    private static final int START_DAY = 1;
    private static final int END_DAY = 31;
    private static final List<Integer> SPECIAL_DAY = List.of(3, 10, 17, 24, 25, 31);

    public Calendar {
        validateOrderDay(visitDay);
    }

    private void validateOrderDay(int visitDay) {
        if (visitDay < 1 || visitDay > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
