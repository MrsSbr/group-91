import performanceTest.PerformanceTest;
import simulation.Election;
import simulation.VotesGenerator;

import java.io.IOException;
import java.util.Collection;
import java.util.Scanner;

public final class Main {
    private static final int CANDIDATE_COUNT = 12;
    private static final int MIN_VOTER_PERCENT = 10;
    private static final int VOTER_COUNT = 300;

    public static void main(String[] args) {

        VotesGenerator votesGenerator = new VotesGenerator(VOTER_COUNT, CANDIDATE_COUNT);

        try {
            votesGenerator.generateToInputStream();
        } catch (IOException e) {
            return;
        }

        Collection<Integer> rawVotes = PerformanceTest.chooseCollection(votesGenerator).get();

        try (Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextInt()) {
                rawVotes.add(scanner.nextInt());
            }
        }

        System.out.printf("--------%nГолоса:%n%s%n", rawVotes);

        Election election = new Election(rawVotes, CANDIDATE_COUNT, MIN_VOTER_PERCENT);
        int candidate = election.countVotes(PerformanceTest.chooseCollection(election));

        System.out.printf(candidate == 0 ?
                          "--------%nНе удалось выбрать представителя%n--------%n" :
                          "--------%nВыбрали представителя %s%n--------%n", candidate);
    }
}