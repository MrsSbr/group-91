package Classes;

public class MammothPrey {
    private String nickname;
    private int weight;
    private int year;

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
