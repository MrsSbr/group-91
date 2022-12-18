package enums;

public enum ListOfNamesOfStudents {
    AGAFON("Агафон", 0),
    ALEXANDER("Александр", 1),
    ALEXEY("Алексей", 2),
    ANATOLIY("Анатолий", 3),
    ARTEM("Артем", 4),
    ATHANASIUS("Афанасий", 5),
    BORIS("Борис", 6),
    DMITRIY("Дмитрий", 7),
    EVGENIY("Евгений", 8),
    ILYA("Илья", 9),
    IVAN("Иван", 10),
    JAN("Ян", 11),
    JAROSLAV("Ярослав", 12),
    KASIMIR("Казимир", 13),
    KIRILL("Кирилл", 14),
    MAKSIM("Макс", 15),
    MARK("Марк", 16),
    MAKAR("Макар", 17),
    MATVEY("Матвей", 18),
    MIKHAIL("Михаил", 19),
    MIROSLAV("Мирослав", 20),
    NIKITA("Никита", 21),
    PAVEL("Павел", 22),
    PETER("Петр", 23),
    ROBERT("Роберт", 24),
    ROMAN("Роман", 25),
    SAVELY("Савелий", 26),
    SERAPHIM("Серафим", 27),
    SERGEI("Сергей", 28),
    STANISLAV("Станислав", 29),
    TROFIM("Трофим", 30),
    VALERY("Валерий", 31),
    VASILIY("Василий", 32),
    VICTOR("Виктор", 33),
    VLADISLAV("Владислав", 34),
    VSEVOLOD("Всеволод", 35);

    private final String name;
    private final int id;

    ListOfNamesOfStudents(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static String getById(int id) {
        for (ListOfNamesOfStudents e : values()) {
            if (e.id == id) {
                return e.name;
            }
        }
        return null;
    }
}