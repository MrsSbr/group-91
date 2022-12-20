package lab3.src.studentClasses;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.IntStream.range;

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
        range(0, SIZE).mapToObj(i -> st.createRandomStudent()).forEach(students::add);
        long finish = System.nanoTime();
        System.out.println("Time ms: " + (finish - start));
    }

    public void fillFromConsole() {
        Student st = new Student();
        range(0, SIZE).mapToObj(i -> st.readStudentFromConsole()).forEach(students::add);
    }

    public void clear() {
        this.students.clear();
    }

    public void findAverageGrades() {
        long start = System.nanoTime();
        students.stream()
                .map(student -> student.nameToString() + " " + student.getAverageGrade()).forEach(System.out::println);

        long finish = System.nanoTime();
        System.out.println("Time ms: " + (finish - start));
    }

    public void findAStudents() {
        long start = System.nanoTime();
        students.stream()
                .filter(Student::isAStudent)
                .map(Student::nameToString)
                .forEach(System.out::println);

        long finish = System.nanoTime();
        System.out.println("Time ms: " + (finish - start));
    }

    public void findStudentGotAllGrades() {
        long start = System.nanoTime();
        students.stream()
                .filter(Student::isGotAllGrades)
                .map(Student::nameToString)
                .forEach(System.out::println);

        long finish = System.nanoTime();
        System.out.println("Time ms: " + (finish - start));
    }
}



