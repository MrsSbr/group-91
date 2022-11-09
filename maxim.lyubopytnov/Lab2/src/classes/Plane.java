package classes;

// Самолет
public abstract class Plane extends MilitaryAviation {
    // Незаметность (в %)
    protected final float obscurity;

    public Plane(String name, float obscurity){
        super(name);
        this.obscurity =obscurity;
    }
}
