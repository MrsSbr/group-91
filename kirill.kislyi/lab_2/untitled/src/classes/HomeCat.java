package classes;

public abstract class HomeCat extends CatImpl {
    protected final int age;

    public HomeCat(String name, int age) {
        super(name);
        this.age = age;
    }
}
