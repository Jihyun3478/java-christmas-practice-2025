package christmas.domain.order;

import java.util.List;

public record Calendar(
        int visitDay
) {
    private static final List<Integer> SPECIAL_DAY = List.of(3, 10, 17, 24, 25, 31);

    public Calendar {
        validateOrderDay(visitDay);
    }

    private void validateOrderDay(int visitDay) {
        if (visitDay < 1 || visitDay > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public boolean isWeekend(int day) {
        return day % 7 == 1 || day % 7 == 2;
    }

    public boolean isSpecial(int day) {
        return SPECIAL_DAY.contains(day);
    }
}
