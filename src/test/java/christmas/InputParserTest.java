package christmas;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.util.InputParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class InputParserTest {
    @Nested
    @DisplayName("메뉴 입력 파싱 예외 테스트")
    class 메뉴_입력_파싱_예외_테스트 {
        @Test
        @DisplayName("중복 메뉴를 입력한 경우, 예외가 발생한다.")
        void 중복_메뉴를_입력한_경우_예외가_발생한다() {
            assertThatThrownBy(() -> InputParser.parseOrder("시저샐러드-1,시저샐러드-1"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        @Test
        @DisplayName("메뉴 형식이 올바르지 않을 경우, 예외가 발생한다.")
        void 메뉴_형식이_올바르지_않을_경우_예외가_발생한다() {
            assertThatThrownBy(() -> InputParser.parseOrder("티본스테이크-1-1"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        @Test
        @DisplayName("메뉴 개수가 숫자가 아닐 경우, 예외가 발생한다.")
        void 메뉴_개수가_숫자가_아닐_경우_예외가_발생한다() {
            assertThatThrownBy(() -> InputParser.parseOrder("티본스테이크-a"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
