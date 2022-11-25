package classes;

public class HummingBird extends FlyingBird {

    private final String name;
    private final int age;
    public HummingBird(int age, String name) {
        super(0.005, 21, "Hummingbird", 40);
        this.age = age;
        this.name = name;
    }

    @Override
    public void walk() {
        System.out.println("I can't walk I'm a hummingbird");
    }

    @Override
    public String toString() {
        return super.toString() + "\nВозраст: " + age;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Integer.hashCode(age);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        HummingBird hummingBird = (HummingBird) obj;
        return super.equals(obj) && name.equals(hummingBird.name) && age == hummingBird.age;
    }
}
