import simulation.Election;
import simulation.VotesGenerator;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public final class Main {
    private final static Logger logger = Logger.getGlobal();
    private static final int CANDIDATE_COUNT = 12;
    private static final int MIN_VOTER_PERCENT = 10;
    private static final int VOTER_COUNT = 300;

    public static void main(String[] args) {

        VotesGenerator votesGenerator = new VotesGenerator(VOTER_COUNT, CANDIDATE_COUNT);

        try {
            votesGenerator.generateToInputStream();

        } catch (IOException e) {
            logger.severe("ошибка при записи случайных чисел в поток ввода");
            return;
        }

        List<Integer> rawVotes = new LinkedList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextInt()) {
                rawVotes.add(scanner.nextInt());
            }
        }

        Election election = new Election(rawVotes, MIN_VOTER_PERCENT);
        int candidate = election.elect();

        String result;
        switch (candidate) {
            case -1 -> result = "Представитель не выбран: нет ни одного голоса";
            case -2 -> result = "Представитель не выбран: не был пройден порог по голосам";
            case -3 -> result = "Представитель не выбран: больше одного кандидата набрали максимальное число голосов";
            default -> result = "Выбрали представителя " + candidate;
        }

        System.out.printf(result);
    }
}