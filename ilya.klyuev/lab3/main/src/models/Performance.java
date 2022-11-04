package models;

public enum Performance {
    ANYUTA("Анюта"),
    CINDERELLA("Золушка"),
    VIRGO("Дева"),
    GISELLE("Жизель"),
    NUTCRACKER("Щелкунчик"),
    MACBETH("Макбет"),
    CORSAIR("Корсар"),
    SYLPTH("Сильфида");

    private int ticketsCount = 0;
    private final String name;

    Performance(String name) {
        this.name = name;
    }

    public void addTicket() {
        ticketsCount++;
    }

    public int getTicketsCount() {
        return ticketsCount;
    }

    @Override
    public String toString() {
        return String.format("%s: Количество билетов = %d", name, ticketsCount);
    }

    public static Performance fromIndex(int index) {
        return Performance.values()[index];
    }

    public static int getPerformancesCount() {
        return Performance.values().length;
    }
}
