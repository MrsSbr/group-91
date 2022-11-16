package studentClasses;

import java.util.*;

public class StudentList {

    private static final int SIZE = 1000;
    private final List<Student> students;
    public StudentList(List<Student> students) {

        this.students = students;
    }

    public StudentList(Boolean isArray) {
        if (isArray) {
            this.students = new ArrayList<>();
        }
        else {
            this.students = new LinkedList<>();
        }
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

    public void clear() {
        this.students.clear();
    }

    public void findAverageGrades() {
        long start = System.nanoTime();
        for (Student student : students) {
            System.out.println(student.nameToString() + " " + student.getAverageGrade());
        }

        long finish = System.nanoTime();
        System.out.println("Time ms: " + (finish - start));
    }

    public void findAStudents() {
        long start = System.nanoTime();
        for (Student student : students) {
            if (student.isAStudent()) {
                System.out.println(student.nameToString());
            }
        }

        long finish = System.nanoTime();
        System.out.println("Time ms: " + (finish - start));
    }

    public void findStudentGotAllGrades() {
        long start = System.nanoTime();
        for (Student student : students) {
            if (student.isGotAllGrades()) {
                System.out.println(student.nameToString());
            }
        }

        long finish = System.nanoTime();
        System.out.println("Time ms: " + (finish - start));
    }
}



