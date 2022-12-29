package models;

import enums.Genre;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;

public class Tasks {
    private static final int AMOUNT_OF_SOLD_GAMES = 7283;
    private static final List<String> GAME_NAMES = List.of("Dota 2", "csgo", "Gta 5", "Cyberpunk", "The forest", "Valheim", "Raft");
    private static final List<Genre> GENRE_LIST = List.of(Genre.values());

    public String randName() {
        StringBuilder stringBuilder = new StringBuilder();
        Random rand = new Random();
        int indexName = rand.nextInt(0, GAME_NAMES.size());
        stringBuilder.append(GAME_NAMES.get(indexName));
        return stringBuilder.toString();
    }

    public Genre randGenre() {
        Random rand = new Random();
        int indexGenre = rand.nextInt(0, GENRE_LIST.size());
        return GENRE_LIST.get(indexGenre);
    }

    public double randPrice() {
        Random random = new Random();
        return random.nextDouble();
    }

    private List<SellGame> bestGamesByGenre(List<SellGame> sellGames) {
        List<SellGame> result = new ArrayList<>();
        Genre bestGenre = null;
        int cntGamesBestGenre = 0;

        for (Genre genre : Genre.values()) {
            int cntGenre = 0;
            for (SellGame game : sellGames) {
                if (game.getGenre().equals(genre)) {
                    cntGenre++;
                }
            }

            if (cntGenre > cntGamesBestGenre) {
                bestGenre = genre;
                cntGamesBestGenre = cntGenre;
            }
        }

        for (SellGame game : sellGames) {
            if (game.getGenre().equals(bestGenre)) {
                result.add(game);
            }
        }

        return result;
    }

    private Month bestMonth(List<SellGame> sellGames) {
        double maxPriceMonth = 0;
        Month bestMonth = null;

        for (Month month : Month.values()) {
            double sumPrice = 0;
            for (SellGame game : sellGames) {
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

    private Set<String> notPopularlyGame(List<SellGame> sellGames) {
        Set<String> GamesNotSoldInLast3Month = new HashSet<>();

        for (SellGame game : sellGames) {
            if (game.getDate().isBefore(LocalDate.now().minusMonths(3))) {
                // добавляются все игры, которые были проданы не за последние 3 месяца
                GamesNotSoldInLast3Month.add(game.getName());
            }
        }

        for (SellGame game : sellGames) {
            if (game.getDate().isAfter(LocalDate.now().minusMonths(3))) {
                // если за последние три месяца игра с таким названием была продана, то она удаляется
                for (int i = 0; i < GamesNotSoldInLast3Month.size(); i++) {
                    if (GamesNotSoldInLast3Month.contains(game.getName())) {
                        GamesNotSoldInLast3Month.remove(game.getName());
                        break;
                    }
                }
            }
        }

        return GamesNotSoldInLast3Month;
    }

    public void task(List<SellGame> sellGames, boolean checkTime) {
        Random random = new Random();
        int minDay = (int) LocalDate.of(1980, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.now().toEpochDay();
        for (int i = 0; i < AMOUNT_OF_SOLD_GAMES; i++) {
            long randomDay = minDay + random.nextInt(maxDay - minDay);
            LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
            sellGames.add(new SellGame(randName(), randGenre(), randomDate, randPrice()));
        }
        long startTime = System.nanoTime();
        List<SellGame> task1 = bestGamesByGenre(sellGames);
        Month task2 = bestMonth(sellGames);
        Set<String> task3 = notPopularlyGame(sellGames);
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
            System.out.println("Список игр проданных хоть раз, но не за последние 3 месяца: ");
            for (String f : task3) {
                System.out.println(f);
            }
        }
    }
}