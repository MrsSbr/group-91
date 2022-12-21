package classes;

import enums.ListOfNamesOfStudents;
import enums.ListOfPatronymicsOfStudents;
import enums.ListOfSubjects;
import enums.ListOfSurnamesOfStudents;

public class Olympiad {
    private final int yearInWhichTheOlympiadWasHeld;
    private final String subjectOnWhichTheOlympiadWasHeld;
    private final int classForWhichTheOlympiadWasIntended;
    private final String studentWhoTookTheFirstPlace;
    private final String studentWhoTookTheSecondPlace;
    private final String studentWhoTookTheThirdPlace;

    public Olympiad() {
        yearInWhichTheOlympiadWasHeld = randBetween(2000, 2022);
        subjectOnWhichTheOlympiadWasHeld = ListOfSubjects.getById(randBetween(0, 14));
        classForWhichTheOlympiadWasIntended = randBetween(1, 11);

        studentWhoTookTheFirstPlace = ListOfSurnamesOfStudents.getById(randBetween(0, 35)) + " " +
                ListOfNamesOfStudents.getById(randBetween(0, 35)) + " " +
                ListOfPatronymicsOfStudents.getById(randBetween(0, 35));

        studentWhoTookTheSecondPlace = ListOfSurnamesOfStudents.getById(randBetween(0, 35)) + " " +
                ListOfNamesOfStudents.getById(randBetween(0, 35)) + " " +
                ListOfPatronymicsOfStudents.getById(randBetween(0, 35));

        studentWhoTookTheThirdPlace = ListOfSurnamesOfStudents.getById(randBetween(0, 35)) + " " +
                ListOfNamesOfStudents.getById(randBetween(0, 35)) + " " +
                ListOfPatronymicsOfStudents.getById(randBetween(0, 35));
    }

    public Olympiad(int yearInWhichTheOlympiadWasHeld, String subjectOnWhichTheOlympiadWasHeld,
                    int classForWhichTheOlympiadWasIntended, String studentWhoTookTheFirstPlace,
                    String studentWhoTookTheSecondPlace, String studentWhoTookTheThirdPlace) {

        this.yearInWhichTheOlympiadWasHeld = yearInWhichTheOlympiadWasHeld;
        this.subjectOnWhichTheOlympiadWasHeld = subjectOnWhichTheOlympiadWasHeld;
        this.classForWhichTheOlympiadWasIntended = classForWhichTheOlympiadWasIntended;
        this.studentWhoTookTheFirstPlace = studentWhoTookTheFirstPlace;
        this.studentWhoTookTheSecondPlace = studentWhoTookTheSecondPlace;
        this.studentWhoTookTheThirdPlace = studentWhoTookTheThirdPlace;
    }

    public int getYearInWhichTheOlympiadWasHeld() {
        return yearInWhichTheOlympiadWasHeld;
    }

    public String getSubjectOnWhichTheOlympiadWasHeld() {
        return subjectOnWhichTheOlympiadWasHeld;
    }

    public int getClassForWhichTheOlympiadWasIntended() {
        return classForWhichTheOlympiadWasIntended;
    }

    public String getStudentWhoTookTheFirstPlace() {
        return studentWhoTookTheFirstPlace;
    }

    public String getStudentWhoTookTheSecondPlace() {
        return studentWhoTookTheSecondPlace;
    }

    public String getStudentWhoTookTheThirdPlace() {
        return studentWhoTookTheThirdPlace;
    }

    private int randBetween(int min, int max) {
        return (int) (Math.random() * (max - min) + 1) + min;
    }
}
