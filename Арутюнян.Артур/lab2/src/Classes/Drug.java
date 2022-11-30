package Classes;

public class Drug extends Pill {

    private int activeSubstance;

    public Drug(String name, int countPerKilo, float price, int activeSubstance)
    {
        super(name, countPerKilo, price);
        this.activeSubstance = activeSubstance;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) { 
            return true; 
        }

        if (!(obj instanceof Drug)) { 
            return false; 
        }
        Drug drug = (Drug) obj;

        return super.equals(drug) && activeSubstance == drug.activeSubstance;
    }

    @Override
    public int hashCode() {
        return super.hashCode()+ activeSubstance;
    }

    @Override
    public int getRecipe(float weight) {
        return (int)weight/activeSubstance;
    }
}
