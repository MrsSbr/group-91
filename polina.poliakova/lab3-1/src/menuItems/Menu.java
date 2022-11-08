package menuItems;

import checkValidatons.InputValidations;
import studentClasses.StudentArrayList;
import studentClasses.StudentLinkedList;

public class Menu {
    public static void mainMenu() {
        int choice = -1;
        while (choice != 0) {

            System.out.println("""
                                        
                    Main menu
                    1. Use ArrayList
                    2. Use LinkedList
                    0. Exit""");
            choice = InputValidations.checkMenuItem();

            choice = switch (choice) {
                case 1 -> {
                    studentArrayListMenu();
                    yield 1;

                }
                case 2 -> {
                    studentLinkedListMenu();
                    yield 2;
                }
                case 0 -> {
                    System.out.println("Bye!");
                    yield 0;
                }
                default -> {
                    System.out.println("Error");
                    yield -1;
                }
            };
        }
    }

    public static void studentArrayListMenu() {

        StudentArrayList sal = new StudentArrayList();

        int choice = -1;
        while (choice != 0) {
            System.out.println("""

                    ArrayListStudents menu
                    1. Fill student from console
                    2. Fill random
                    3. Print students with average grades
                    4. Print all A-Students
                    5. Print students who got all grades
                    0. Exit""");
            choice = InputValidations.getNumberInRange(0, 5);

            choice = switch (choice) {
                case 1 -> {
                    sal.Clear();
                    sal.fillFromConsole();
                    yield 1;
                }
                case 2 -> {
                    sal.Clear();
                    sal.fillRandom();
                    yield 2;
                }
                case 3 -> {
                    sal.findAverageGrades();
                    yield 3;
                }
                case 4 -> {
                    sal.findAStudents();
                    yield 4;
                }
                case 5 -> {
                    sal.findStudentGotAllGrades();
                    yield 4;
                }
                case 0 -> {
                    System.out.println("Bye!");
                    yield 0;
                }
                default -> {
                    System.out.println("Error");
                    yield -1;
                }
            };
        }
    }

    public static void studentLinkedListMenu() {

        StudentLinkedList sll = new StudentLinkedList();

        int choice = -1;
        while (choice != 0) {
            System.out.println("""

                    LinkedListStudents menu
                    1. Fill student from console
                    2. Fill random
                    3. Print students with average grades
                    4. Print all A-Students
                    5. Print students who got all grades
                    0. Exit""");

            choice = InputValidations.getNumberInRange(0, 5);
            choice = switch (choice) {
                case 1 -> {
                    sll.Clear();
                    sll.fillFromConsole();
                    yield 1;
                }
                case 2 -> {
                    sll.Clear();
                    sll.fillRandom();
                    yield 2;
                }
                case 3 -> {
                    sll.findAverageGrades();
                    yield 3;
                }
                case 4 -> {
                    sll.findAStudents();
                    yield 4;
                }
                case 5 -> {
                    sll.findStudentGotAllGrades();
                    yield 4;
                }
                case 0 -> {
                    System.out.println("Bye!");
                    yield 0;
                }
                default -> {
                    System.out.println("Error");
                    yield -1;
                }
            };
        }
    }
}
