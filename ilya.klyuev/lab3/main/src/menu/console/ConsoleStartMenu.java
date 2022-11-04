package menu.console;

import input.console.ConsoleInput;
import menu.StartMenu;
import menu.StartMenuDialogResult;

import java.util.logging.Logger;

public class ConsoleStartMenu implements StartMenu {

    private static final int START_MENU_ITEM = 1;
    private static final int END_MENU_ITEM = 3;
    private final Logger logger;

    public ConsoleStartMenu(Logger logger) {

        this.logger = logger;
    }

    @Override
    public StartMenuDialogResult showDialog() {
        int menuItem;
        ConsoleInput consoleInput = new ConsoleInput(logger);

        showMenu();
        menuItem = consoleInput.inputIntInRange("Выберите пункт", START_MENU_ITEM, END_MENU_ITEM);

        return switch (menuItem) {
            case 1 -> StartMenuDialogResult.USERMODE;
            case 2 -> StartMenuDialogResult.TESTMODE;
            default -> StartMenuDialogResult.EXIT;
        };
    }

    private void showMenu() {
        System.out.println("1: Режим пользователя");
        System.out.println("2: Протестировать время работы ArrayList и LinkedList");
        System.out.println("3: Выход");
    }
}
