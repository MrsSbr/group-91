package models;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.stream.Stream;
import enums.*;
public class Tasks {
    private static final int AMOUNT_OF_SELLGAMES = 7283;
    public String randName() {
        StringBuilder stringBuilder = new StringBuilder();
        Random rand = new Random();
        int indexName = rand.nextInt(0,NamesGames.values().length);
        stringBuilder.append(Arrays.stream(NamesGames.values()).toList().get(indexName));
        return stringBuilder.toString();
    }

    public String randGenre() {
        StringBuilder stringBuilder = new StringBuilder();
        Random rand = new Random();
        int indexGenre = rand.nextInt(0,Genre.values().length);
        stringBuilder.append(Arrays.stream(Genre.values()).toList().get(indexGenre));
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
        for (var genre : Genre.values()) {
            int countGenre = 0;
            for (var game : sellGames) {
                if (game.getGenre().equals(genre.toString())) {
                    countGenre++;
                }
            }
            if (countGenre > countGamesBestGanre) {
                bestGanre = genre.toString();
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