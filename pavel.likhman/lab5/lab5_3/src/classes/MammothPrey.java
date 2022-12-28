package classes;

public class MammothPrey {
    private final String nickname;
    private final int weight;
    private final int year;

    public MammothPrey(String name, int weight, int year) {
        this.nickname = name;
        this.weight = weight;
        this.year = year;
    }

    public String getNickname() {
        return nickname;
    }

    public int getWeight() {
        return weight;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return "Name: " + nickname + " ; Weight: " + weight + " ; Year: " + year + "\n";
    }
}
