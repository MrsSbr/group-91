package models;

import java.time.Month;

public class MonthStats {
    public Month month;
    public double cntFeed;
    public double cntMilk;

    public MonthStats(Month month, double feed, double milk) {
        this.month = month;
        this.cntFeed = feed;
        this.cntMilk = milk;
    }

    public double getRatio() {
        return cntFeed / cntMilk;
    }
}
