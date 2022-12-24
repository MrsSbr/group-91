import java.util.Arrays;

public class Main {

    private static final String pathPrefix = "ilya.klyuev/lab8/poems/";
    private static final String pushkinPoemPath = pathPrefix + "pushkin.txt";
    private static final String eseninPoemPath = pathPrefix + "esenin.txt";
    private static final String cvetaevaPoemPath = pathPrefix + "cvetaeva.txt";
    private static final String shnurPoemPath = pathPrefix + "shnur.txt";

    public static void main(String[] args) {
        StringBuilder napkin = new StringBuilder();

        var poets = Arrays.asList(
                new Poet(pushkinPoemPath, napkin),
                new Poet(eseninPoemPath, napkin),
                new Poet(cvetaevaPoemPath, napkin),
                new Poet(shnurPoemPath, napkin)
        );

        poets.forEach(Poet::start);

        for (Poet poet : poets) {
            try {
                poet.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(napkin);
    }
}
