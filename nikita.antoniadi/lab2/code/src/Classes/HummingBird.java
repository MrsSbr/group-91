package Classes;

public class HummingBird extends FlyingBird {

    public HummingBird() {
        super(0.005, 21, "Hummingbird");
    }

    @Override
    public void walk() {
        System.out.println("I can't walk I'm a hummingbird");
    }
}
