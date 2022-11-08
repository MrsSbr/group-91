import classes.MilitaryShip;
import classes.AircraftCarrier;
import classes.Battleship;
import classes.Destroyer;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MilitaryShip yamato = new Battleship(256, 39, 11,
                63200, "Yamato", 63);
        MilitaryShip gerald = new AircraftCarrier(337, 78, 12,
                100000, "Gerald R. Ford", 90);
        MilitaryShip daring = new Destroyer(152, 21, 5,
                7350, "Daring type 45", 50);

        List<MilitaryShip> ships = new ArrayList<>();
        ships.add(yamato);
        ships.add(gerald);
        ships.add(daring);

        for (MilitaryShip ship : ships) {
            ship.info();
            System.out.println(ship);

            if (ship instanceof AircraftCarrier ac) {
                ac.launchAircraft();
            }
        }
    }
}
