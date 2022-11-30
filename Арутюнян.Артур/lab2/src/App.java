import java.util.ArrayList;

import Classes.*;
import Enums.FluidType;
import Interfaces.InterfaceMedicine;

public class App {
    public static void main(String[] args) {
        var list = new ArrayList<InterfaceMedicine>();
        list.add(new Drug("Met", 2, 100, 1));
        list.add(new Pilule("Pilule", 10, 1, FluidType.alcohol));
        list.add(new Solute("sol", 5, 10));
        for(var iMed: list)
        {
            System.out.println(iMed.toString());
            if (iMed instanceof Solute) {
                Solute solute = (Solute) iMed;
                solute.drink(1);
                System.out.println(solute.toString());
            }
        }

        var pil1 = new Pilule("Pilule", 10, 1, FluidType.alcohol);
        var pil2 = new Pilule("Pilule", 10, 1, FluidType.water);

        for(var iMed: list)
        {
            if(iMed.equals(pil1))
            {
                System.out.println(iMed.toString());
                System.out.println(iMed.equals(pil2));
            }
        }
    }
}
