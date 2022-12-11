import classes.Coat;
import classes.FurCoat;
import classes.Outerwear;
import classes.Raincoat;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Outerwear coat = new Coat("autumn", "black", "into-the-cage", 44, "Zara", "double-breasted");
        Outerwear furcoat = new FurCoat("winter", "brown", "none", 52, "Elena", 145);
        Outerwear raincoat = new Raincoat("spring", "beige", "none", 48, "Lime", "Duspo");

        List <Outerwear> clothess = new ArrayList<>();
        clothess.add (coat);
        clothess.add (furcoat);
        clothess.add (raincoat);

        for (Outerwear clothes : clothess) {
            clothes.info();
            System.out.println(clothes);

            if (clothes instanceof Raincoat tc) {
                tc.buyRaincoat();
            }
        }
    }
}