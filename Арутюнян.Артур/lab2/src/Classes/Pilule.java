package Classes;
import Enums.FluidType;

public class Pilule extends Pill {

    private FluidType requiredFluid;

    public Pilule(String name, int countPerKilo, float price, FluidType requiredFluid)
    {
        super(name, countPerKilo, price);
        this.requiredFluid = requiredFluid;
    }

    public String getRequiredFluid()
    {
        return requiredFluid.toString();
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj == this) { 
            return true; 
        }

        if (!(obj instanceof Pilule)) { 
            return false; 
        }
        Pilule drug = (Pilule) obj;

        return super.equals(drug) && requiredFluid == drug.requiredFluid;
    }

    @Override
    public String toString() {
        return super.toString() + "need: "+getRequiredFluid()+'\n';
    }
}
