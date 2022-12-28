package models;

import java.time.LocalDate;
import java.util.*;

public class User {
    public static final int NUM_OF_SONGS = 100;

    private final List<ListeningSong> songs;

    public User(List<ListeningSong> songs) {
        this.songs = songs;
    }

    public static User generateArrayListSongs() {
        List<ListeningSong> songs = new ArrayList<>();

        for (int i = 0; i < NUM_OF_SONGS; i++) {
            songs.add(ListeningSong.generateSongListening());
        }

        return new User(songs);
    }

    public static User generateLinkedListSongs() {
        List<ListeningSong> songs = new LinkedList<>();

        for (int i = 0; i < NUM_OF_SONGS; i++) {
            songs.add(ListeningSong.generateSongListening());
        }

        return new User(songs);
    }

    public List<String> getAllSongPlaylist() {
        List<String> curPlaylist = new ArrayList<>();
        for (ListeningSong song : songs) {
            String curSongName = song.getSongName();
            if (!curPlaylist.contains(curSongName)) {
                curPlaylist.add(curSongName);
            }
        }
        return curPlaylist;
    }

    public List<String> getSongPlaylistNotListeningInThreeMonths() {
        List<String> curPlaylist = new ArrayList<>();
        LocalDate lastDate = LocalDate.now().minusMonths(3);
        for (ListeningSong song : songs) {
            String curSongName = song.getSongName();
            if (!curPlaylist.contains(curSongName) && song.getDate().isBefore(lastDate)) {
                curPlaylist.add(curSongName);
            }
        }
        return curPlaylist;
    }

    public List<String> getFavoriteSongs() {
        Map<String, Integer> listeningAmount = new HashMap<>();
        for (ListeningSong song : songs) {
            String curSongName = song.getSongName();
            listeningAmount.merge(curSongName, 1, (curr, def) -> curr + 1);
        }

        List<String> favoriteSongs = new ArrayList<>();
        Integer curMaxAmount = 0;
        for (String songName : listeningAmount.keySet()) {
            Integer curAmount = listeningAmount.get(songName);

            if (curAmount.equals(curMaxAmount)) {
                favoriteSongs.add(songName);
            } else if (curAmount > curMaxAmount) {
                curMaxAmount = curAmount;
                favoriteSongs.clear();
                favoriteSongs.add(songName);
            }
        }

        return favoriteSongs;
    }

    public int getAmountOfListeningForLastMonthForUser(String songName) {
        LocalDate firstDate = LocalDate.now().minusMonths(1);
        int curAmount = 0;
        for (ListeningSong song : songs) {
            if (songName.equals(song.getSongName()) && song.getDate().isAfter(firstDate)) {
                curAmount++;
            }
        }
        return curAmount;
    }
}
