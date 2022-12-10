package performances;

public enum Performance {
    ANYUTA("Анюта"),
    CINDERELLA("Золушка"),
    VIRGO("Дева"),
    GISELLE("Жизель"),
    NUTCRACKER("Щелкунчик"),
    MACBETH("Макбет"),
    CORSAIR("Корсар"),
    SYLPTH("Сильфида");
    private final String name;

    Performance(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Performance fromIndex(int index) {
        return Performance.values()[index];
    }

    public static int getPerformancesCount() {
        return Performance.values().length;
    }
}
