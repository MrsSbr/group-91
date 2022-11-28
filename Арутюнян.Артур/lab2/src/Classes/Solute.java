package Classes;
import java.util.Objects;

import Interfaces.InterfaceMedicine;

public class Solute implements InterfaceMedicine {
    private String name;
    private float volume;
    private float price;

    public Solute(String name, float volume, float price)
    {
        this.price = price;
        this.name = name;
        this.volume = volume;
    }

    public float getVolume()
    {
        return volume;
    }

    public void open() {
        
    }

    public boolean drink(float dv)
    {
        if(dv>volume)
            return false;
        volume-=dv;
        return true;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + "\nprice: "+ price+'\n' + volume + "l\n";
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pilule pilule)) {
            return false;
        }
        return name.equals(pilule.name);
    }
}
