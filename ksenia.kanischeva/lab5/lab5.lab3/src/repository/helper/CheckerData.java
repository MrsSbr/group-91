package repository.helper;

public class CheckerData {

    private CheckerData() {
    }

    public static boolean isCorrectPlayer(int number) {
        return number >= 1 && number <= 22;
    }

}
