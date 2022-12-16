package TribeDatawork;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TribeEntry {
    private final LocalDate date;
    private final int mammothWeight;
    private final String hunterName;

    private static final Logger logger = Logger.getLogger(TribeEntry.class.getName());

    public TribeEntry(String hunterName, int mammothWeight, LocalDate date) {
        this.date = date;
        this.mammothWeight = mammothWeight;
        this.hunterName = hunterName;
    }

    public int getMammothWeight() {
        return mammothWeight;
    }

    public String getHunterName() {
        return hunterName;
    }

    public LocalDate getDate() {
        return date;
    }

    // Имя, вес, дата
    public static TribeEntry getTribeFromString(String data) {
        try {
            String[] split = data.split(", ");
            return new TribeEntry(split[0], Integer.parseInt(split[1]), LocalDate.parse(split[2]));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Creation Error Found on string " + data, e);
        }
        return null;
    }
}
