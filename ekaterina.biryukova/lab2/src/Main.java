import classes.DecorativeCosmetic;
import classes.Eyeshadow;
import classes.Lipstick;
import classes.ToneCream;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DecorativeCosmetic powderPuff = new Lipstick(36, "velvety", "lips", 5, "NYX", "red");
        DecorativeCosmetic bronzeGirl = new Eyeshadow(36, "shimmer", "eyes", 34, "KLOG", 9);
        DecorativeCosmetic richGold = new ToneCream(36, "creamy", "face", 100, "ENOUGH", 16);

        List<DecorativeCosmetic> cosmetics = new ArrayList<>();
        cosmetics.add(powderPuff);
        cosmetics.add(bronzeGirl);
        cosmetics.add(richGold);

        for (DecorativeCosmetic cosmetic : cosmetics) {
            cosmetic.info();
            System.out.println(cosmetic);

            if (cosmetic instanceof ToneCream tc) {
                tc.buyToneCream();
            }

        }
    }
}