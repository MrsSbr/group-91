package studentClasses;

import checkValidatons.InputValidations;

import java.util.*;

/*Вводится информация о студенте (ФИО) и список его оценок (400 шт)*/
public class Student {
    private final static int GRADES_NUMBER = 400;
    private String firstName;
    private String lastName;
    private String patronymic;
    private List<Integer> grades = new ArrayList<>();

    public Student(String firstName, String lastName, String patronymic, List<Integer> grades) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.grades = grades;
    }

    public Student() {

    }

    public double getAverageGrade() {
        int sum = 0;
        for (int i = 0; i < GRADES_NUMBER; i++) {
            sum += grades.get(i);
        }
        return sum / (double) GRADES_NUMBER;
    }

    public boolean isGotAllGrades() {
        final int NUM_OF_POSSIBLE_GRADES = 5;
        List<Boolean> possibleGrades = new ArrayList<>(NUM_OF_POSSIBLE_GRADES);
        for (int i = 0; i<NUM_OF_POSSIBLE_GRADES;i++)
            possibleGrades.add(false);
        for (int i = 0; i < GRADES_NUMBER; i++) {
            possibleGrades.set(grades.get(i)-1, true);

        }
        return false;
    }





































    public boolean isAStudent() {
        for (int i = 0; i < GRADES_NUMBER; i++)
            if (grades.get(i) != 5 )
                return false;

        return true;
    }

    public Student readStudentFromConsole() {




        Student student = new Student();
        student.firstName = InputValidations.checkName();
        student.lastName = InputValidations.checkName();
        student.patronymic = InputValidations.checkName();
        for (int i = 0; i < Student.GRADES_NUMBER; i++) {
            student.grades.add( 1 + (int)(Math.random() * 5));
        }

        return student;
    }

    public String generateName() {
        final int NAME_LENGTH = 8;
        String charsCaps = "abcdefghijklmnopqrstwvuxyz";
        Random rnd = new Random();
        StringBuilder name = new StringBuilder();

        for (int i = 0; i < NAME_LENGTH; i++) {
            name.append(charsCaps.charAt(rnd.nextInt(charsCaps.length())));
        }

        return name.toString();
    }

    public Student createRandomStudent() {
        Student student = new Student();
        student.firstName = generateName();
        student.lastName = generateName();
        student.patronymic = generateName();
        for (int i = 0; i < Student.GRADES_NUMBER; i++) {
            student.grades.add(1 + (int)(Math.random() * 5));
        }

        return student;
    }

    public String nameToString() {
        return this.lastName + " " + this.firstName;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Student student = (Student) o;
        return lastName.equals(student.lastName) && firstName.equals(student.firstName) && patronymic.equals(student.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName);
    }
}
