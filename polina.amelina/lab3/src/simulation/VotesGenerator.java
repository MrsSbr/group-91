package simulation;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class VotesGenerator extends Testable {

    private final int voterCount;
    private final int candidateCount;

    public VotesGenerator(int voterCount, int candidateCount) {
        this.voterCount = voterCount;
        this.candidateCount = candidateCount;
        allowedCollections = new IntegerCollectionSupplier[] {ArrayList::new, LinkedList::new, PriorityQueue::new, ArrayDeque::new};
    }

    public void generateToCollection(IntegerCollectionSupplier collectionSupplier) {

        Collection<Integer> rawVotes = collectionSupplier.get();
        Random random = new Random();

        for (int i = 0; i < voterCount; i++) {
            rawVotes.add(random.nextInt(1, candidateCount + 1));
        }
    }

    public void generateToInputStream() throws IOException {

        Random random = new Random();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < voterCount; i++) {
            stringBuilder.append(random.nextInt(1, candidateCount + 1)).append(" ");
        }

        try (InputStream inputStream = new ByteArrayInputStream(stringBuilder.toString().getBytes(StandardCharsets.UTF_8))) {
            System.setIn(inputStream);
        } catch (IOException e) {
            Logger.getGlobal().log(Level.SEVERE, "ошибка при записи случайных чисел в поток ввода", e);
            throw e;
        }
    }

    @Override
    public void test(IntegerCollectionSupplier collectionSupplier) {
        generateToCollection(collectionSupplier);
    }
}