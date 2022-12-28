package tests;

import models.ListeningSong;
import models.User;
import models.UsersBase;

public class Testing {
    public static long getWorkTime(UsersBase usersBase) {
        String testSongName = ListeningSong.generateSongName();

        long startTime = System.currentTimeMillis();

        usersBase.getAmountOfListeningForLastMonth(testSongName);
        for (int i = 0; i < UsersBase.NUM_OF_USERS; i++) {
            User curUser = usersBase.getUser(i);
            curUser.getAllSongPlaylist();
            curUser.getSongPlaylistNotListeningInThreeMonths();
            curUser.getFavoriteSongs();
        }

        return System.currentTimeMillis() - startTime;
    }

    public static void printMostEffective() {
        long workTimeArrayList = getWorkTime(UsersBase.generateArrayListUsers());
        long workTimeLinkedList = getWorkTime(UsersBase.generateLinkedListUsers());

        System.out.println(workTimeArrayList > workTimeLinkedList
                ? "Использование LinkedList более эффективное"
                : "Использование ArrayList более эффективное");
    }
}
