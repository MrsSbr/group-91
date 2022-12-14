package classes;

import interfaces.Plant;

import java.util.Objects;

public abstract class Insectivores implements Plant{
    protected String trapType;
    protected int height;
    public Insectivores(String trapType, int height){
        this.trapType = trapType;
        this.height = height;
    }

    public abstract void waitingInsect();

    public void growthUp(){
        System.out.printf("Насекомоядное растение выросло, его рост: %d мм%n", this.height);
    }

    public void eating(){
        System.out.printf("Питание: Фотосинтез + насекомыми, используя ловушку типа %s %n", this.trapType);
    }

    public void blossom(){
        System.out.println("Насекомоядное растение в процессе цветения");
    }

    public String toString(){
        return "\nТип ловушки: " + this.trapType + "; рост: " + this.height + " мм";
    }

    public boolean equals(Object ob) {
        if (ob == this) {
            return true;
        }

        if (ob == null || ob.getClass() != getClass()) {
            return false;
        }

        Insectivores ins = (Insectivores) ob;

        return Objects.equals(this.trapType, ins.trapType) &&
                this.height == ins.height;
    }

    public int hashCode(){
        return Objects.hash(trapType, height);
    }

}
