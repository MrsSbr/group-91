package classes;

import java.util.Objects;

public class Bus {
    public String busName;
    private int dayCash;

    public Bus(String busName) {
        this.busName = busName;
        this.setCash(0);
    }

    public void setCash(int cash) {
        dayCash = cash;
    }

    public int dayEnds() {
        return dayCash;
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == this) {
            return true;
        }

        if (ob == null || ob.getClass() != getClass()) {
            return false;
        }

        Bus bus = (Bus) ob;

        return this.dayCash == bus.dayCash &&
                Objects.equals(this.busName, bus.busName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayCash, busName);
    }
}
