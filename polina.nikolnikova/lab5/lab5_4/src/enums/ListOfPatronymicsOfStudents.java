package enums;

public enum ListOfPatronymicsOfStudents {
    AGAFONOVICH("Агафонович", 0),
    ALEKSANDROVICH("Александрович", 1),
    ALEXEYEVICH("Алексеевич", 2),
    ANATOLYEVICH("Анатольевич", 3),
    ARTEMIEVICH("Артемьевич", 4),
    AFANASIEVICH("Афанасьевич", 5),
    BORISOVICH("Борисович", 6),
    DMITRIEVICH("Дмитриевич", 7),
    EVGENIEVICH("Евгеньевич", 8),
    ILYCH("Ильич", 9),
    IVANOVICH("Иванович", 10),
    JANOVICH("Янович", 11),
    JAROSLAVOVICH("Ярославович", 12),
    KASIMIROVICH("Казимирович", 13),
    KIRILLOVICH("Кириллович", 14),
    MAKSIMOVICH("Максимович", 15),
    MARKOVICH("Маркович", 16),
    MAKAROVICH("Макарович", 17),
    MATVEYEVICH("Матвеевич", 18),
    MIKHAILOVICH("Михаилович", 19),
    MIROSLAVOVICH("Мирославович", 20),
    NIKITCH("Никитич", 21),
    PAVLOVICH("Павлович", 22),
    PETROVICH("Петрович", 23),
    ROBERTOVICH("Робертович", 24),
    ROMANOVICH("Романович", 25),
    SAVELYEVICH("Савельевич", 26),
    SERAPHIMOVICH("Серафимович", 27),
    SERGEEVICH("Сергеевич", 28),
    STANISLAVOVICH("Станиславович", 29),
    TROFIMOVICH("Трофимович", 30),
    VALERIEVICH("Валерьевич", 31),
    VASILIEVICH("Васильевич", 32),
    VICTOROVICH("Викторович", 33),
    VLADISLAVOVICH("Владиславович", 34),
    VSEVOLODOVICH("Всеволодович", 35);

    private final String patronymic;
    private final int id;

    ListOfPatronymicsOfStudents(String patronymic, int id) {
        this.patronymic = patronymic;
        this.id = id;
    }

    public static String getById(int id) {
        for (ListOfPatronymicsOfStudents e : values()) {
            if (e.id == id) {
                return e.patronymic;
            }
        }
        return null;
    }
}
