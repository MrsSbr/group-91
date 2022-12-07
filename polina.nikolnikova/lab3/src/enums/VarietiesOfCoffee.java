package enums;

public enum VarietiesOfCoffee {
    AMERICANO("Американо"),
    CAPPUCCINO("Капучино"),
    DOPPIO("Доппио"),
    ESPRESSO("Эспрессо"),
    FLAT_WHITE("Флэт Уайт"),
    FRAPPE("Фраппе"),
    GLACE("Глясе"),
    ICE_COFFEE("Холодный кофе"),
    IRISH_COFFEE("Кофе по-ирландски"),
    LATTE("Латте"),
    LONG_BLACK("Лонг блэк"),
    MACCHIATO("Макиато"),
    MOCHA("Мокко"),
    RAF("Раф"),
    VENICE_COFFEE("Кофе по-венски");

    private final String name;

    VarietiesOfCoffee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}