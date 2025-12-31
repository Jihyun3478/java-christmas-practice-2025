package christmas;

import java.util.Arrays;

public enum Menu {
    MUSHROOM_SOUP(MenuType.APPETIZER, "양송이수프",6_000),
    TAPAS(MenuType.APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(MenuType.APPETIZER, "시저샐러드", 8_000),

    T_BONE_STEAK(MenuType.MAIN, "티본스테이크", 55_000),
    BARBEQUE_RIBS(MenuType.MAIN, "바비큐립", 54_000),
    SEAFOOD_PASTA(MenuType.MAIN, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MenuType.MAIN, "크리스마스파스타", 25_000),

    CHOCOLATE_CAKE(MenuType.DESSERT, "초코케이크", 15_000),
    ICE_CREAM(MenuType.DESSERT, "아이스크림", 5_000),

    ZERO_COKE(MenuType.DRINK, "제로콜라", 3_000),
    RED_WINE(MenuType.DRINK, "레드와인", 60_000),
    CHAMPAGNE(MenuType.DRINK, "샴페인", 25_000),
    ;

    private MenuType menuType;
    private String name;
    private int price;

    Menu(MenuType menuType, String name, int price) {
        this.menuType = menuType;
        this.name = name;
        this.price = price;
    }

    public static Menu getMenu(String menuName) {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name.equals(menuName))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."));
    }

    public boolean isDrink() {
        return this.menuType == MenuType.DRINK;
    }
}
