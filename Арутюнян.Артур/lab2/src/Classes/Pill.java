package Classes;
import Interfaces.InterfaceMedicine;

import java.util.Objects;

public abstract class Pill implements InterfaceMedicine{

    protected final int countPerKilo;
    private float price;
    protected String name;

    public Pill(String name, int countPerKilo, float price) {
        this.name = name;
        this.countPerKilo = countPerKilo;
        this.price = price;
    }

    public void open() {
    }

    public int getRecipe(float weight)
    {
        return (int)weight/countPerKilo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pill pill)) {
            return false;
        }
        return name.equals(pill.name);
    }

    @Override
    public String toString() {
        return name + "\nprice: "+ price+'\n' + countPerKilo + "/kg\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
