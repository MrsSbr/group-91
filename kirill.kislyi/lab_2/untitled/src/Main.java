
import classes.Siberian;
import classes.Sphinx;
import interfaces.Cat;
import interfaces.Scratching;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        var items = getItems();

        for (var item : items) {
            if (item instanceof Scratching scratching) {
                scratching.scratch("Чермет");
                item.castrate();
            }

            System.out.println("\nА мы его ещё и подстричь решили...\n");
            item.haircut();
            System.out.println("__________________________\n");
        }
    }



    private static ArrayList<Cat> getItems(){
        var items = new ArrayList<Cat>();
        items.add(new Siberian("Black", "Cucumber", 8));
        items.add(new Sphinx("Saitama", 1));
        items.add(new Siberian("White", "King", 9));
        items.add(new Sphinx("Gordon", 3));
        items.add(new Siberian("Rainbow", "Snoop Dog",14));
        return items;
    }
}