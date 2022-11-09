package classes;

import interfaces.Flying;

public class FlyingBird extends NotFlyingBird implements Flying {

    public FlyingBird(double weight, double wingspan, String speciesName) {
        super(weight, wingspan, speciesName);
    }

    @Override
    public void fly() {
        System.out.println(speciesName + " flies");
    }
}