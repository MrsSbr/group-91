import Classes.Bird;
import Classes.FlyingBird;
import Classes.HummingBird;
import Classes.NotFlyingBird;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Bird flyingBird = new FlyingBird(4.5, 205, "Bald eagle");
        Bird notFlyingBird = new NotFlyingBird(5.5, 100, "Magellanic penguin");
        Bird hummingBird = new HummingBird();

        List<Bird> birds = new ArrayList<>(3);

        birds.add(flyingBird);
        birds.add(notFlyingBird);
        birds.add(hummingBird);

        for (Bird bird : birds) {
            System.out.println(bird.toString());
            bird.walk();
            if (bird instanceof FlyingBird) {
                ((FlyingBird) bird).fly();
            }
        }
    }
}