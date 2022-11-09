import classes.Bomber;
import classes.FighterJet;
import classes.Helicopter;
import interfaces.AttackCity;
import interfaces.Aviation;

import java.util.ArrayList;

public class Main {

    public static void main(final String[] args) {
        var items = getItems();
        for (var item : items) {
            if (item instanceof AttackCity attackCity) {
                attackCity.nameAttackCity("Vietnam");
            } else {
                item.refueling();
            }
            System.out.println();
        }
    }

    private static ArrayList<Aviation> getItems() {
        var items = new ArrayList<Aviation>();
        items.add(new Bomber("TU-160", 70, 45000));
        items.add(new FighterJet("SU-27", 57, 4900));
        items.add(new FighterJet("MiG-29", 61, 5300));
        items.add(new Bomber("SU-34", 89, 36000));
        items.add(new Helicopter("KA-50", 5));
        return items;
    }

}