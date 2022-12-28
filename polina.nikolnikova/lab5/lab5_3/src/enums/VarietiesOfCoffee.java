package enums;

public enum VarietiesOfCoffee {
    AMERICANO("Американо", 0),
    CAPPUCCINO("Капучино", 1),
    DOPPIO("Доппио",2),
    ESPRESSO("Эспрессо", 3),
    FLAT_WHITE("Флэт Уайт", 4),
    FRAPPE("Фраппе", 5),
    GLACE("Глясе", 6),
    ICE_COFFEE("Холодный кофе", 7),
    IRISH_COFFEE("Кофе по-ирландски", 8),
    LATTE("Латте", 9),
    LONG_BLACK("Лонг блэк", 10),
    MACCHIATO("Макиато", 11),
    MOCHA("Мокко", 12),
    RAF("Раф", 13),
    VENICE_COFFEE("Кофе по-венски", 14);

    private final String name;
    private final int id;

    VarietiesOfCoffee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static String getById(int id) {
        for (VarietiesOfCoffee e : values()) {
            if (e.id == id) {
                return e.name;
            }
        }
        return null;
    }
}