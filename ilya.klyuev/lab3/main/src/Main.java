import controllers.PerformancesEmployeesController;
import factories.ListChoice;
import factories.ListFactory;
import input.console.EmployeesAddFromConsole;
import menu.MainMenu;
import menu.StartMenu;
import menu.StartMenuDialogResult;
import menu.console.ConsoleMainMenu;
import menu.console.ConsoleStartMenu;
import storages.EmployeeStorage;

import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getGlobal();

    private static final int RANDOM_GENERATIONS_COUNT = 200;

    public static void main(String[] args) {

        StartMenu startMenu = new ConsoleStartMenu(LOGGER);
        StartMenuDialogResult startMenuDialogResult = startMenu.showDialog();

        if (startMenuDialogResult == StartMenuDialogResult.USERMODE) {
            startUserMode();
        } else if (startMenuDialogResult == StartMenuDialogResult.TESTMODE) {
            startTestMode();
        }
    }

    private static void startUserMode() {
        ListFactory.selectList(ListChoice.ARRAYLIST);

        PerformancesEmployeesController performancesEmployeesController = new PerformancesEmployeesController(
                new EmployeesAddFromConsole(LOGGER),
                new EmployeeStorage(LOGGER)
        );
        MainMenu mainMenu = new ConsoleMainMenu(performancesEmployeesController, LOGGER);
        mainMenu.show();
    }

    private static void startTestMode() {
        TestTime testTime = new TestTime(LOGGER);

        ListFactory.selectList(ListChoice.LINKEDLIST);
        long executionTimeWithLinkedList = testTime.test(RANDOM_GENERATIONS_COUNT);
        System.out.printf("LinkedList ms: %d\n", executionTimeWithLinkedList);

        ListFactory.selectList(ListChoice.ARRAYLIST);
        long executionTimeWithArrayList = testTime.test(RANDOM_GENERATIONS_COUNT);
        System.out.printf("ArrayList ms: %d\n", executionTimeWithArrayList);

        if (executionTimeWithArrayList < executionTimeWithLinkedList) {
            System.out.println("ArrayList выгоднее использовать для данной задачи");
        } else {
            System.out.println("LinkedList выгоднее использовать для данной задачи");
        }
    }
}