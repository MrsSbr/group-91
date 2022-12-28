package classes;

public record Olympiad(int yearInWhichTheOlympiadWasHeld, String subjectOnWhichTheOlympiadWasHeld,
                       int classForWhichTheOlympiadWasIntended, String studentWhoTookTheFirstPlace,
                       String studentWhoTookTheSecondPlace, String studentWhoTookTheThirdPlace) {

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
}