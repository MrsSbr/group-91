package models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UsersBase {
    public static final int NUM_OF_USERS = 500;

    private List<User> users;

    public UsersBase(List<User> users) {
        this.users = users;
    }

    public User getUser(int index) {
        return users.get(index);
    }

    public static UsersBase generateArrayListUsers() {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < NUM_OF_USERS; i++) {
            users.add(User.generateArrayListSongs());
        }

        return new UsersBase(users);
    }

    public static UsersBase generateLinkedListUsers() {
        List<User> users = new LinkedList<>();

        for (int i = 0; i < NUM_OF_USERS; i++) {
            users.add(User.generateLinkedListSongs());
        }

        return new UsersBase(users);
    }

    public int getAmountOfListeningForLastMonth(String songName) {
        int curAmount = 0;
        for (User user : users) {
            curAmount += user.getAmountOfListeningForLastMonthForUser(songName);
        }
        return curAmount;
    }
}
