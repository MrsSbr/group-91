/*Описать иерархию классов для книжного магазина, которая должна содержать:

- интерфейс
- абстрактный класс
- несколько наследников одного класса (или реализаций интерфейса)
- переопределение метода toString
- переопределение методов equals и hashCode
- переопределение публичного метода класса-предка
- использование конструктора с параметрами класса-предка

В коде программы продемонстрировать использование принципов ООП на основе созданной иерархии классов.
*/

import classes.Book;
import classes.ArtisticLiterature;
import classes.EducationLiterature;
import classes.ScientificLiterature;

import java.util.ArrayList;
import java.util.List;

public class Bookstore {
    public static void main(String[] args) {

        Book artisticLiterature = new ArtisticLiterature("Денискины рассказы", "Виктор Юзефович " +
                "Драгунский", 7, 170, "Литературный цикл", "Первый день, Похититель собак," +
                " Рыцари, Слон и радио, Старый мореход");

        Book educationLiterature = new EducationLiterature("Учебное пособие", "Гарик Гагикович" +
                " Петросян", 12, 500, "Алгебра", 11);

        Book scientificLiterature = new ScientificLiterature("Познание", "Игорь Петрович Мешков",
                3, 740, "Ядерная физика", "Начальный");

        List<Book> books = new ArrayList<>();
        books.add(artisticLiterature);
        books.add(educationLiterature);
        books.add(scientificLiterature);

        for (Book book : books) {
            System.out.println(book);


            if (book instanceof EducationLiterature castedEducationLiterature) {
                castedEducationLiterature.hasDisk();
            }

            System.out.println();
        }
    }
}