package enums;

public enum ListOfSubjects {
    ASTRONOMY("Астрономия", 0),
    BIOLOGY("Биология", 1),
    CHEMISTRY("Химия", 2),
    INFORMATION_TECHNOLOGY("Информатика", 3),
    GEOGRAPHY("География", 4),
    HISTORY("История", 5),
    LIFE_SAFETY("Основы безопасности жизнедеятельности", 6),
    LITERATURE("Литература", 7),
    MATHEMATICS("Математика", 8),
    PE("Физкультура", 9),
    PHYSICALS("Физика", 10),
    RUSSIAN("Русский язык", 11),
    SCIENCE("Естествознание", 12),
    SOCIAL_SCIENCE("Обществознание", 13),
    TECHNOLOGY("Труды", 14);

    private final String name;
    private final int id;

    ListOfSubjects(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static String getById(int id) {
        for (ListOfSubjects e : values()) {
            if (e.id == id) {
                return e.name;
            }
        }
        return null;
    }
}

