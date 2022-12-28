package UI;

import models.UsersBase;
import tests.Testing;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static void printMainMenu() {
        System.out.print("""
                1. Сгенерировать список пользователей с ArrayList
                2. Сгенерировать список пользователей с LinkedList
                0. Выход
                Ваш выбор: """);
    }

    private static void printUsersWorkMenu() {
        System.out.print("""
                1. Количество прослушиваний песни за последний месяц
                2. Подборка прослушанных песен для выбранного пользователя
                3. Подборка песен, которые выбранный пользователь не слушал последние 3 месяца
                4. Любимый трек для выбранного пользователя
                5. Посчитать полное время работы для всех методов
                0. Выбрать другую реализацию списка
                Ваш выбор: """);
    }

    public static void mainMenu() {
        printMainMenu();

        UsersBase usersBase = null;
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                usersBase = UsersBase.generateArrayListUsers();
                break;
            case 2:
                usersBase = UsersBase.generateLinkedListUsers();
                break;
            case 3:
                Testing.printMostEffective();
                break;
            case 0:
                System.out.println("Завершение работы...");
                return;
            default:
                System.out.println("Ошибка ввода!");
                mainMenu();
                break;
        }
        usersWorkMenu(usersBase);
    }

    private static void usersWorkMenu(UsersBase usersBase) {
        printUsersWorkMenu();

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        int userNum = 0;

        switch (choice) {
            case 1:
                System.out.print("Введите название песни: ");
                String songName = sc.nextLine();
                int amount = usersBase.getAmountOfListeningForLastMonth(songName);
                System.out.println("Количество прослушиваний выбранного трека: " + amount);
                break;
            case 2:
                System.out.println("Введите номер пользователя от 1 до " + UsersBase.NUM_OF_USERS + ": ");
                userNum = sc.nextInt();
                while (userNum < 1 || userNum > 500) {
                    System.out.println("Ошибка ввода! Введите номер пользователя от 1 до " + UsersBase.NUM_OF_USERS + ": ");
                    userNum = sc.nextInt();
                }
                List<String> playlistAllSong = usersBase.getUser(userNum - 1).getAllSongPlaylist();
                Printer.printPlaylist(playlistAllSong);
                break;
            case 3:
                System.out.println("Введите номер пользователя: ");
                userNum = sc.nextInt();
                while (userNum < 1 || userNum > 500) {
                    System.out.println("Ошибка ввода! Введите номер пользователя от 1 до " + UsersBase.NUM_OF_USERS + ": ");
                    userNum = sc.nextInt();
                }
                List<String> playlistNotListening = usersBase.getUser(userNum - 1).getSongPlaylistNotListeningInThreeMonths();
                Printer.printPlaylist(playlistNotListening);
                break;
            case 4:
                System.out.println("Введите номер пользователя: ");
                userNum = sc.nextInt();
                while (userNum < 1 || userNum > 500) {
                    System.out.println("Ошибка ввода! Введите номер пользователя от 1 до " + UsersBase.NUM_OF_USERS + ": ");
                    userNum = sc.nextInt();
                }
                List<String> favoriteSongs = usersBase.getUser(userNum - 1).getFavoriteSongs();
                Printer.printPlaylist(favoriteSongs);
                break;
            case 5:
                System.out.printf("Время работы: %d мс\n", Testing.getWorkTime(usersBase));
                break;
            case 0:
                System.out.println("Завершение работы...");
                return;
            default:
                System.out.println("Ошибка ввода!");
                break;
        }
        usersWorkMenu(usersBase);
    }
}
