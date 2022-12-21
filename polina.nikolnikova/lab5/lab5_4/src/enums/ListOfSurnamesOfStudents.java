package enums;

public enum ListOfSurnamesOfStudents {
    AMELIN("Амелин", 0),
    ANTONIADI("Антониади", 1),
    AVERIN("Аверин", 2),
    AVERKIN("Аверкин", 3),
    BARANNIKOV("Баранников", 4),
    BARKOV("Барков", 5),
    BEZUGLOV("Безуглов", 6),
    BIRYUKOV("Бирюков", 7),
    BUKHTIN("Бухтин", 8),
    BUNIN("Бунин", 9),
    DENISOV("Денисов", 10),
    GOLOVIN("Головин", 11),
    KLEPIKOV("Клепиков", 12),
    KLYUEV("Клюев", 13),
    KOTOV("Котов", 14),
    LUKIN("Лукин", 15),
    NIKOLAYCHIK("Николайчик", 16),
    NIKOLENKO("Николенко", 17),
    NIKOLNIKOV("Никольников", 18),
    NOVIKOV("Новиков", 19),
    PANFILOV("Панфилов", 20),
    PARINOV("Паринов", 21),
    PETROV("Петров", 22),
    PUSHKIN("Пушкин", 23),
    PUTIN("Путин", 24),
    PUTRIN("Путрин", 25),
    ROMANOV("Романов", 26),
    RYABCHIKOV("Рябчиков", 27),
    RYZKOV("Рыжков", 28),
    SKIBIN("Скибин", 29),
    SLAVIN("Славин", 30),
    STUPINSKY("Ступинский", 31),
    YAKUNIN("Якунин", 32),
    YESENIN("Есенин", 33),
    ZABOLOTNY("Заболотный", 34),
    ZVEREV("Зверев", 35);

    private final String surname;
    private final int id;

    ListOfSurnamesOfStudents(String surname, int id) {
        this.surname = surname;
        this.id = id;
    }

    public static String getById(int id) {
        for (ListOfSurnamesOfStudents e : values()) {
            if (e.id == id) {
                return e.surname;
            }
        }
        return null;
    }
}