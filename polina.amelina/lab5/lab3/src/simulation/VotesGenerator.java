package simulation;

import methodNameGetter.MethodNameGetter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class VotesGenerator {
    private final static Logger logger = Logger.getGlobal();
    private final int voterCount;
    private final int candidateCount;

    public VotesGenerator(int voterCount, int candidateCount) {
        this.voterCount = voterCount;
        this.candidateCount = candidateCount;
    }

    public void generateToInputStream() throws IOException {
        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        Random random = new Random();
        String votes = IntStream
                .generate(() -> random.nextInt(1, candidateCount + 1))
                .limit(voterCount)
                .boxed()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
        logger.info("Generated votes: " + votes);

        try (InputStream inputStream = new ByteArrayInputStream(votes.getBytes(StandardCharsets.UTF_8))) {
            System.setIn(inputStream);
            logger.exiting(getClass().getName(), MethodNameGetter.getMethodName());

        } catch (IOException e) {
            logger.throwing(getClass().getName(), MethodNameGetter.getMethodName(), e);
            throw e;
        }
    }
}