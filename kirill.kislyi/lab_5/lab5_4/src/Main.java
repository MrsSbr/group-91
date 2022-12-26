//В постоянных попытках найти ответ на главный вопрос жизни, вселенной и всего такого,
//мыши регулярно строят суперкомпьютеры, и анализируют их работу, записывая результаты в файл в следующем формате:
//название суперкомпьютера;время его работы;ответ
//Найти все ответы, которые были выданы 3-мя и более суперкомпьютерами
//Для каждого ответа найти суммарное время его вычисления
//Вывести суперкомпьютеры в порядке возрастания их времени работы
//в задаче запрещено использовать элементы функционального программирования


import Help.Helper;
import Models.Miceputers;
import Tasks.Tasks;
import Prints.Prints;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    private static final String DATASET_PATH = "kirill.kislyi/lab_4/src/info/info.txt";


    public static void main(String[] args) {
        logger.log(Level.INFO, "Start micegram");
        Miceputers miceputers = new Miceputers();
        miceputers.fileToMiceputers(DATASET_PATH);
        Prints.printMenu();

        int answer = Helper.getIntInDiapason(0, 3);
        switch (answer) {

            case 1 -> Tasks.Task1(miceputers);
            case 2 -> Tasks.Task2(miceputers);
            case 3 -> Tasks.Task3(miceputers);
            case 0 -> {
                System.out.println("Завершение");
                return;
            }
        }
    }
}
