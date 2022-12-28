package UI;

import java.util.List;

public class Printer {
    public static void printPlaylist(List<String> playlist){
        if (playlist.isEmpty()) {
            System.out.println("Выбранный плейлист пуст");
            return;
        }
        System.out.println(playlist);
    }
}
