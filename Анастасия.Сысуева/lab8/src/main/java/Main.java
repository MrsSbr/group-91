import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final Path PATH_ESENIN_FILE_CONST = Path.of("Анастасия.Сысуева/lab8/data/Esenin.txt");
    private static final Path PATH_PUSKIN_FILE_CONST = Path.of("Анастасия.Сысуева/lab8/data/Puskin.txt");
    private static final Path PATH_SHNUR_FILE_CONST = Path.of("Анастасия.Сысуева/lab8/data/Shnur.txt");
    private static final Path PATH_TSVETAEVA_FILE_CONST = Path.of("Анастасия.Сысуева/lab8/data/Tsvetaeva.txt");

    public static List<String> readFile(Path path) {
        List<String> lines;
        try (Stream<String> lineStream = Files.lines(path)) {
            lines = lineStream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines;
    }

    public static void main(String[] args) {
        StringBuilder napkin = new StringBuilder();
        List<Poet> poets = Arrays.asList(new Poet(readFile(PATH_ESENIN_FILE_CONST), napkin),
                new Poet(readFile(PATH_PUSKIN_FILE_CONST), napkin),
                new Poet(readFile(PATH_SHNUR_FILE_CONST), napkin), new Poet(readFile(PATH_TSVETAEVA_FILE_CONST), napkin));

        poets.forEach(Poet::start);
        poets.forEach(poet -> {
            try {
                poet.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        System.out.println("Итоговый стих на салфетке: \n\n" + napkin);
    }
}
