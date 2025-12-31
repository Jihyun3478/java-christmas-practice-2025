package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CalendarTest {
    @Nested
    @DisplayName("달력 생성 예외 테스트")
    class 달력_생성_예외_테스트 {
        @ParameterizedTest
        @ValueSource(ints = {0, 32})
        @DisplayName("식당 예상 방문 날짜가 1 이상 31 이하의 숫자가 아닌 경우, 예외가 발생한다.")
        void 식당_예상_방문_날짜가_1이상_31이하의_숫자가_아닌_경우_예외가_발생한다(int visitDay) {
            assertThatThrownBy(() -> new Calendar(visitDay))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
}
