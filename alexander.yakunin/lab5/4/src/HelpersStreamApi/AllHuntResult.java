package HelpersStreamApi;

public class AllHuntResult {
    private int weight;
    private int countOfHunt;

    public AllHuntResult(int weight, int countOfHunt) {
        this.countOfHunt = countOfHunt;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getCountOfHunt() {
        return countOfHunt;
    }

    public AllHuntResult addHunt(int weight) {
        this.weight += weight;
        this.countOfHunt += 1;
        return this;
    }
}
