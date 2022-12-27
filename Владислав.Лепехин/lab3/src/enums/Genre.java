package enums;

public enum Genre {
    SCARY("scary"),
    STORY("story"),
    RPG("rpg");
    private final String title;
    Genre(String title) {
        this.title = title;
    }
    @Override
    public String toString() {
        return title;
    }
}