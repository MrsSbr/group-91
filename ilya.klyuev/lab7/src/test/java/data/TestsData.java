package data;

import java.math.BigDecimal;

public class TestsData {

    public static final Student ILYA_STUDENT = new Student(0, "Клюев Илья Алексеевич", BigDecimal.valueOf(2500), 3, 91);
    public static final Student STUDENT_WITH_APOSTROPHE = new Student(1, "Ani'mani Mach'lac", BigDecimal.valueOf(3500), 2, 9);

    public static final Person VASILIY_IVANOV = new Person("Vasiliy", "Ivanov");
}
