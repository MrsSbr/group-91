package models;

import java.time.*;
import java.util.Random;

public class ListeningSong {
    private static final LocalDate MAX_DATE = LocalDate.now();
    private static final int DAYS_PER_YEAR = 365;

    private static final String[] SONG_NAMES = new String[]{"Church Asylum", "Dream Aria", "Extermination",
            "Deutschland", "Our Solemn Hour", "Hootsforce", "Forgiven", "Faint", "Painkiller", "Angel Of Death",
            "Puttin' On The Ritz", "Holiday", "Ode To My Family", "Those Nights", "Wicked Game", "Famous Last Words",
            "Beyond the Matrix", "Enclosed", "Sanctified with Dynamite", "Godless", "A Real Hero", "Threshold",
            "Centuries", "Higher Than Heaven", "Falling Inside the Black", "Deja Vu", "Troubled Times", "Teenagers",
            "Venom of Venus", "No", "Battle in the Sky", "Once Upon a December", "We Are Number One",
            "Young And Beautiful", "Na Na Na (Na Na Na Na Na Na Na Na Na)", "Paint It Black", "Zombie",
            "Still Loving You", "You Spin Me Round", "Herr Mannelig", "Dead Boys Don't Cry", "Words",
            "When You Find Me", "Nightmare", "Nostalgia", "decadence", "After Dark", "Around The World",
            "Harder Better Faster Stronger", "One More Time", "Veridis Quo", "Nightvision", "Last Desire",
            "Tears of a Dragonheart", "Gimme Chocolate!!", "City Of Stars", "Madame Lulu", "A Man Without Love",
            "Take on Me", "Get Lucky", "Instant Crush", "Nightcall", "Pneumonoultramicroscopicsilicovolcanoconiosis"};

    private String songName;
    private LocalDate date;

    public ListeningSong(String songName, LocalDate date) {
        this.songName = songName;
        this.date = date;
    }

    public String getSongName() {
        return songName;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Название: " + songName + "Дата прослушивания: " + date.toString();
    }

    public static String generateSongName() {
        Random rnd = new Random();
        return SONG_NAMES[rnd.nextInt(0, SONG_NAMES.length)];
    }

    public static LocalDate generateDate() {
        Random rnd = new Random();
        return MAX_DATE.minusDays(rnd.nextInt(0, DAYS_PER_YEAR));
    }

    public static ListeningSong generateSongListening() {
        return new ListeningSong(generateSongName(), generateDate());
    }
}
