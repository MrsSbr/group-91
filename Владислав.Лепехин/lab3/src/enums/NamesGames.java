package enums;

public enum NamesGames {
    BEST("best"),
    TOP("top"),
    WUNDERFUL("wunderful");
    private final String title;
    NamesGames(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title;
    }
}