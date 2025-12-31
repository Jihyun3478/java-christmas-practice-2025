package christmas;

import java.util.EnumMap;

public class InputParser {
    public static int parseVisitDay(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public static EnumMap<Menu, Integer> parseOrder(String input) {
        EnumMap<Menu, Integer> orders = new EnumMap<>(Menu.class);
        String[] items = input.split(",");

        for (String item : items) {
            String[] split = item.split("-");
            if (split.length != 2) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            Menu menu = Menu.getMenu(split[0]);
            int count = 0;
            try {
                count = Integer.parseInt(split[1]);
            } catch (NumberFormatException exception) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            if (orders.containsKey(menu)) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            orders.put(menu, count);
        }

        return orders;
    }
}
