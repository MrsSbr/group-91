package Classes;

public abstract class Bird {

    protected final double averageWeight;

    protected final double averageWingspan;

    public Bird(double averageWeight, double averageWingspan) {
        this.averageWeight = averageWeight;
        this.averageWingspan = averageWingspan;
    }

    public void eat() {
        System.out.println("Птица ест");
    }

    public abstract void walk();
}
