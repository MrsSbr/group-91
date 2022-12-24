package Models;

import java.util.*;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Supermiceputer {
    private final String name;
    private final String answer;
    private final int time;


    private static final Logger logger = Logger.getLogger(Supermiceputer.class.getName());

    public Supermiceputer(String name, String answer, int time) {
        this.name = name;
        this.answer = answer;
        this.time = time;
    }

    public String getName () {
        return name;
    }

    public String getAnswer () {
        return answer;
    }

    public int getTime () {
        return time;
    }

    public static Supermiceputer convertStringToSPM (String data) {
        try {
            String[] split = data.split("; ");
            Supermiceputer mps = new Supermiceputer(split[0], split[2], Integer.parseInt(split[1]));
            return mps;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Creation Error Found on string " + data, e);
        }
        return null;
    }
}
