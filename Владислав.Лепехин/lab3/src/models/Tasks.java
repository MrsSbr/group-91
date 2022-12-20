package models;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Stream;

public class Tasks {
    private static final int AMOUNT_OF_SELLGAMES = 7283;
    private static final List<String> name = new ArrayList<>(Arrays.asList("sdfgfd", "dfghgf", "jfhsdf", "asdwe",
            "hfghjhg", "kyufk", "dfshy", "jtynxvcb", "estyrt", "fghd", "e5yhj", "edhy"));
    private static final List<String> genre = new ArrayList<>(Arrays.asList("scary", "story", "rpg"));


    public String randName() {
        StringBuilder stringBuilder = new StringBuilder();
        Stream.of(name)
                .forEach(Collections::shuffle);
        Random rand = new Random();
        int indexName = rand.nextInt(name.size());
        stringBuilder.append(name.get(indexName));
        return stringBuilder.toString();
    }

    public String randGenre() {
        StringBuilder stringBuilder = new StringBuilder();
        Stream.of(genre)
                .forEach(Collections::shuffle);
        Random rand = new Random();
        int indexGenre = rand.nextInt(genre.size());
        stringBuilder.append(genre.get(indexGenre));
        return stringBuilder.toString();
    }

    public double randPrice() {
        Random random = new Random();
        return random.nextDouble();
    }

    private List<SellGame> bestGamesByGanre(List<SellGame> sellGames) {

        List<SellGame> result = new ArrayList<>();
        String bestGanre = "";
        int countGamesBestGanre = 0;
        for (var genre : genre) {
            int countGenre = 0;
            for (var game : sellGames) {

                if (game.getGenre().equals(genre)) {

                    countGenre++;

                }

            }

            if (countGenre > countGamesBestGanre) {

                bestGanre = genre;
                countGamesBestGanre = countGenre;

            }

        }
        for (var game : sellGames) {

            if (game.getGenre().equals(bestGanre)) {

                result.add(game);

            }

        }

        return result;

    }

    private Month bestMonth(List<SellGame> sellGames) {

        double maxPriceMonth = 0;
        Month bestMonth = null;

        for (var month : Month.values()) {

            double sumPrice = 0;
            for (var game : sellGames) {
                if (game.getDate().getMonth() == month) {

                    sumPrice += game.getPrice();

                }

            }
            if (sumPrice > maxPriceMonth) {

                maxPriceMonth = sumPrice;
                bestMonth = month;

            }

        }

        return bestMonth;

    }

    private boolean isNotSellInLastThreeMonth(LocalDate date) {

        int countDaysDate = date.getYear() * 365 + date.getMonthValue() * 30 + date.getDayOfMonth();
        LocalDate now = LocalDate.now();
        int countDayNow = now.getYear() * 365 + now.getMonthValue() * 30 + now.getDayOfMonth();
        return countDayNow - countDaysDate > 90;

    }

    private String notPopularyGame(List<SellGame> sellGames) {


        List<String> nameGamesSellLastThreeMonth = new ArrayList<>();
        List<String> nameGamesNotSellLastThreeMonth = new ArrayList<>();
        for (var game : sellGames) {
            if (isNotSellInLastThreeMonth(game.getDate())) {

                if (!nameGamesSellLastThreeMonth.contains(game.getName())) {
                    if (!nameGamesSellLastThreeMonth.contains(game.getName())) {

                        nameGamesNotSellLastThreeMonth.add(game.getName());

                    }


                }

            } else {

                nameGamesNotSellLastThreeMonth.remove(game.getName());

                if (!nameGamesSellLastThreeMonth.contains(game.getName())) {

                    nameGamesSellLastThreeMonth.add(game.getName());

                }

            }


        }
        if (nameGamesNotSellLastThreeMonth.size() != 0) {

            return nameGamesNotSellLastThreeMonth.get(0);

        }

        return "Такой игры нет!";

    }


    public void task(List<SellGame> sellGames, boolean checkTime) {
        Random random = new Random();
        int minDay = (int) LocalDate.of(1980, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2022, 1, 1).toEpochDay();
        for (int i = 0; i < AMOUNT_OF_SELLGAMES; i++) {
            long randomDay = minDay + random.nextInt(maxDay - minDay);
            LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
            sellGames.add(new SellGame(randName(), randGenre(), randomDate, randPrice()));
        }
        long startTime = System.nanoTime();
        List<SellGame> task1 = bestGamesByGanre(sellGames);
        Month task2 = bestMonth(sellGames);
        String task3 = notPopularyGame(sellGames);
        if (checkTime) {
            startTime = System.nanoTime() - startTime;
            System.out.printf("Elapsed %,9.3f ms\n", startTime / 1_000_000.0);
        }
        if (!checkTime) {
            for (int i = 0; i < AMOUNT_OF_SELLGAMES; i++) {
                System.out.println(sellGames.get(i).toString());
            }
            //task1
            System.out.println("Список игр самого популярного жанра");
            for (SellGame f : task1) {
                System.out.println(f.toString());
            }
            //task2
            System.out.println("месяц наибольшей выручки " + task2.toString());
            //task3
            System.out.println("Игра хоть раз проданная но не продававшаяся как минимум последние 3 месяца: " + task3);
        }
    }
}
