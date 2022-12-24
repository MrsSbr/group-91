import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Poet extends Thread {

    private final StringBuilder napkin;
    private final List<String> poemLines;

    public Poet(String poemPath, StringBuilder napkin) {
        this.napkin = napkin;

        try (var lines = Files.lines(Paths.get(poemPath))) {
            poemLines = lines.toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        for (String line : poemLines) {
            synchronized (napkin) {
                napkin.append(line).append("\n");
            }
            try {
                Thread.sleep((long) ((Math.random() * 100) + 50));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
