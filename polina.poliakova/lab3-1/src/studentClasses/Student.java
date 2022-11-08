package studentClasses;

import checkValidatons.InputValidations;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
/*Вводится информация о студенте (ФИО) и список его оценок (400 шт)*/
public class Student {
    private final static int GRADES_NUMBER = 400;
    private String firstName;
    private String lastName;
    private String patronymic;
    private static int[] grades = new int[GRADES_NUMBER];

    public Student(String firstName, String lastName, String patronymic, int[] grades) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.grades = Arrays.copyOf(grades, grades.length);
    }

    public Student() {

    }

    public double getAverageGrade() {
        int sum=0;
        for (int i=0; i<GRADES_NUMBER; i++) {
            sum += grades[i];
        }
        return sum/(double)GRADES_NUMBER;
    }

    public boolean isGotAllGrades() {
        final int NUM_OF_POSSIBLE_GRADES = 5;
        boolean[] possibleGrades = new boolean[NUM_OF_POSSIBLE_GRADES];
        for (int i=0; i<GRADES_NUMBER; i++) {
            possibleGrades[grades[i]] = true;
            if (checkBoolArray(possibleGrades))
                return true;
        }
        return false;
    }

    private boolean checkBoolArray(boolean[] array) {
        boolean res = true;
        for (boolean b : array) res &= b;
        return res;
    }

    public boolean isAStudent() {
        for (int i=0; i<GRADES_NUMBER; i++)
            if (grades[i]!=5)
                return false;
        return true;
    }


    public Student readStudentFromConsole() {
        Random rand = new Random();
        Student student = new Student();
        student.firstName = InputValidations.checkName();
        student.lastName = InputValidations.checkName();
        student.patronymic = InputValidations.checkName();

        for (int i = 0; i < Student.GRADES_NUMBER; i++) {
            Student.grades[i] = rand.nextInt(5) + 1;
        }
        return student;
    }

    public String generateName() {
        final int NAME_LENGTH = 8;
        String charsCaps = "abcdefghijklmnopqrstwvuxyz"; //ABCDEFGHIJKLMNOPQRSTUVWXYZ
        Random rnd = new Random();
        StringBuilder name = new StringBuilder();

        for (int i = 0; i < NAME_LENGTH; i++) {
            name.append(charsCaps.charAt(rnd.nextInt(charsCaps.length())));
        }
        return name.toString();
    }

    public Student createRandomStudent() {
        Random rand = new Random();
        Student student = new Student();
        student.firstName = generateName();
        student.lastName = generateName();
        student.patronymic = generateName();
        for (int i = 0; i < Student.GRADES_NUMBER; i++) {
            grades[i] = rand.nextInt(5) + 1;
        }
        return student;
    }
    public String nameToString() {
        return this.lastName + " " + this.firstName ;
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
        return lastName.equals(student.lastName) &&  firstName.equals(student.firstName) &&  patronymic.equals(student.patronymic);

    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName);
    }
}
