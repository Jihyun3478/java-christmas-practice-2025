package christmas;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.EnumMap;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class OrderTest {
    @Nested
    @DisplayName("주문 생성 예외 테스트")
    class 주문_생성_예외_테스트 {
        @Test
        @DisplayName("고객이 메뉴판에 없는 메뉴를 입력하는 경우, 예외가 발생한다.")
        void 고객이_메뉴판에_없는_메뉴를_입력하는_경우_예외가_발생한다() {
            assertThatThrownBy(() -> Menu.getMenu("트러플뇨끼"))
                    .isInstanceOf(IllegalStateException.class)
                    .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        @Test
        @DisplayName("음료만 주문할 경우, 예외가 발생한다.")
        void 음료만_주문할_경우_예외가_발생한다() {
            EnumMap<Menu, Integer> orders = new EnumMap<>(Menu.class);
            orders.put(Menu.getMenu("제로콜라"), 5);
            orders.put(Menu.getMenu("레드와인"), 5);
            orders.put(Menu.getMenu("샴페인"), 5);

            assertThatThrownBy(() -> new Order(orders))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }

        @Test
        @DisplayName("메뉴의 개수가 1 미만 또는 20개 초과일 경우, 예외가 발생한다.")
        void 메뉴의_개수가_1개_미만_또는_20개_초과일_경우_예외가_발생한다() {
            EnumMap<Menu, Integer> orders = new EnumMap<>(Menu.class);
            orders.put(Menu.getMenu("티본스테이크"), 10);
            orders.put(Menu.getMenu("해산물파스타"), 11);

            assertThatThrownBy(() -> new Order(orders))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
