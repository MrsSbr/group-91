package lab3.src.studentClasses;

import lab3.src.checkValidations.InputValidations;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Student {
    private final static int GRADES_NUMBER = 400;
    private String firstName;
    private String lastName;
    private String patronymic;
    private List<Integer> grades;

    public Student(String firstName, String lastName, String patronymic, List<Integer> grades) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.grades = grades;
    }

    public Student() {

    }

    public double getAverageGrade() {
        return grades.stream()
                .mapToInt(a -> a)
                .average()
                .orElse(0);
    }

    public boolean isGotAllGrades() {
        return grades.stream()
                .distinct()
                .count() == 5;
    }

    public boolean isAStudent() {
        return grades.stream()
                .allMatch(x -> x == 5);
    }

    private List<Integer> generateListGrades() {
        return new Random()
                .ints(GRADES_NUMBER, 1, 6)
                .boxed()
                .collect(Collectors.toList());
    }

    public Student readStudentFromConsole() {
        Student student = new Student();
        student.firstName = InputValidations.checkName();
        student.lastName = InputValidations.checkName();
        student.patronymic = InputValidations.checkName();
        student.grades = generateListGrades();

        return student;
    }

    public String generateName() {
        final int NAME_LENGTH = 8;
        String charsCaps = "abcdefghijklmnopqrstwvuxyz-'";
        Random rnd = new Random();
        return IntStream.range(0, NAME_LENGTH)
                .mapToObj(i -> String.valueOf(charsCaps.charAt(rnd.nextInt(charsCaps.length()))))
                .collect(Collectors.joining());
    }

    public Student createRandomStudent() {
        Student student = new Student();
        student.firstName = generateName();
        student.lastName = generateName();
        student.patronymic = generateName();
        student.grades = generateListGrades();

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
