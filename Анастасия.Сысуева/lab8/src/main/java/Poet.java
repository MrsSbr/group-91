import java.util.List;

public class Poet extends Thread {
    private final List<String> poem;
    private final StringBuilder napkin;

    public Poet(List<String> poem, StringBuilder newPoem) {
        this.poem = poem;
        this.napkin = newPoem;
    }

    public void run() {
        poem.forEach(offer -> {
            synchronized (napkin) {
                napkin.append(offer).append("\n");
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
