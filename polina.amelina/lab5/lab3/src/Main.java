import simulation.Election;
import simulation.VotesGenerator;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.IntStream;

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
            logger.severe(e.toString());
            return;
        }

        List<Integer> rawVotes;

        try (Scanner scanner = new Scanner(System.in)) {
            rawVotes = IntStream
                    .generate(scanner::nextInt)
                    .limit(VOTER_COUNT)
                    .boxed()
                    .toList();
        }

        Election election = new Election(rawVotes, CANDIDATE_COUNT, MIN_VOTER_PERCENT);

        if (election.checkVotes()) {
            System.out.println("Представитель не выбран: найдены недопустимые голоса");
            return;
        }

        int candidate = election.elect();

        String result;
        switch (candidate) {
            case -1 -> result = "Представитель не выбран: не был пройден порог по голосам";
            case -2 -> result = "Представитель не выбран: больше одного кандидата набрали максимальное число голосов";
            default -> result = "Выбрали представителя " + candidate;
        }

        System.out.println(result);
    }
}