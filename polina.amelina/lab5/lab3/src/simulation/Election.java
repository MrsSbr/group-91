package simulation;

import methodNameGetter.MethodNameGetter;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public final class Election {
    private final static Logger logger = Logger.getGlobal();
    private final List<Integer> rawVotes;
    private final int candidateCount;
    private final int minVoterPercent;

    public Election(List<Integer> rawVotes, int candidateCount, int minVoterPercent) {
        this.rawVotes = rawVotes;
        this.candidateCount = candidateCount;
        this.minVoterPercent = minVoterPercent;
    }

    public int elect() {
        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        Map<Integer, Long> countedVotes = rawVotes
                .stream()
                .collect(Collectors.groupingBy(
                        x -> x,
                        Collectors.counting()
                ));
        logger.info("Counted up votes: " + countedVotes);

        long maxVotes = countedVotes
                .values()
                .stream()
                .max(Long::compare)
                .orElseThrow();
        logger.info("Got max votes: " + maxVotes);

        double threshold = rawVotes.size() / 100.0 * minVoterPercent;
        logger.info("Threshold: " + threshold);

        if (maxVotes < threshold) {
            logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), 0);
            return -1;
        }

        List<Integer> candidates = countedVotes
                .entrySet()
                .stream()
                .filter(x -> x.getValue().equals(maxVotes))
                .map(Map.Entry::getKey)
                .toList();
        logger.info("Got candidates with max votes: " + candidates);

        if (candidates.size() > 1) {
            logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), 0);
            return -2;
        }

        Integer candidate = candidates
                .stream()
                .findFirst()
                .orElseThrow();

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), candidate);
        return candidate;
    }
}