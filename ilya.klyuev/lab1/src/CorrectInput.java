import java.util.Scanner;

public class CorrectInput {
    private int inputInt() {
        int number = 0;
        boolean isCorrectInput = false;
        Scanner scanner = new Scanner(System.in);

        while (!isCorrectInput) {
            try {
                number = scanner.nextInt();
                isCorrectInput = true;
            } catch (Exception exception) {
                System.out.println("Произошла ошибка при вводе. Повторите попытку");
                scanner.nextLine();
                exception.printStackTrace();
            }
        }
        return number;
    }

    public int inputIntInRange(String message, int leftBound, int rightBound) {
        int number = 0;
        boolean isCorrectInput = false;

        while (!isCorrectInput) {
            System.out.println(message);
            System.out.printf("Введите число от %d до %d\n", leftBound, rightBound);
            number = inputInt();
            isCorrectInput = number >= leftBound && number <= rightBound;
            if (!isCorrectInput) {
                System.out.println("Число не входит в заданный диапазон. Повторите попытку");
            }
        }
        return number;
    }

    public int[] inputTemperatures() {
        int temperaturesNumber = inputIntInRange("Введите количество дней", 1, 105);
        int[] temperatures = new int[temperaturesNumber];

        for (int i = 0; i < temperaturesNumber; i++) {
            String message = String.format("Введите температуру за %d день", i + 1);
            temperatures[i] = inputIntInRange(message, 30, 100);
        }
        return temperatures;
    }
}
