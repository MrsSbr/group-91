package lab3.src.menuItems;

import lab3.src.checkValidations.InputValidations;
import lab3.src.studentClasses.StudentList;

public class Menu {
    public static void mainMenu() {
        int choice = -1;
        while (choice != 0) {

            System.out.println("""
                                        
                    Main menu
                    1. Use ArrayList
                    2. Use LinkedList
                    0. Exit""");
            choice = InputValidations.getNumberInRange(0, 2);

            choice = switch (choice) {
                case 1 -> {
                    studentListMenu(true);
                    yield 1;
                }
                case 2 -> {
                    studentListMenu(false);
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

    public static void studentListMenu(Boolean isArray) {
        StudentList sal = new StudentList(isArray);

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
                    sal.clear();
                    sal.fillFromConsole();
                    yield 1;
                }
                case 2 -> {
                    sal.clear();
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
}
