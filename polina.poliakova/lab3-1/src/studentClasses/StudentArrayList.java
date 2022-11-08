package studentClasses;

import java.util.*;

public class StudentArrayList {

    private static final int SIZE = 1000;
    private final List<Student> students;

    public StudentArrayList() {

        this.students = new ArrayList<>();

    }

    public void fillRandom() {

        long start = System.nanoTime();
        Student st = new Student();
        for (int i = 0; i < SIZE; i++) {
            students.add(st.createRandomStudent());
        }

        long finish = System.nanoTime();
        long elapsed = finish - start;
        System.out.println("Time ms: " + elapsed);
    }

    public void fillFromConsole() {
        Student st = new Student();
        for (int i = 0; i < SIZE; i++) {
            students.add(st.readStudentFromConsole());
        }
    }

    public void Clear() {
        this.students.clear();
    }

    public void findAverageGrades() {

        long start = System.nanoTime();
        for (Student student : students) {
            System.out.println(student.nameToString() + " " + student.getAverageGrade());
        }

        long finish = System.nanoTime();
        long elapsed = finish - start;
        System.out.println("Time ms: " + elapsed);
    }

    public void findAStudents() {

        long start = System.nanoTime();
        for (Student student : students) {
            if (student.isAStudent())
                System.out.println(student.nameToString());
        }

        long finish = System.nanoTime();
        long elapsed = finish - start;
        System.out.println("Time ms: " + elapsed);
    }

    public void findStudentGotAllGrades() {
        long start = System.nanoTime();

        for (Student student : students) {
            if (student.isGotAllGrades())
                System.out.println(student.nameToString());
        }

        long finish = System.nanoTime();
        long elapsed = finish - start;
        System.out.println("Time ms: " + elapsed);
    }
}



