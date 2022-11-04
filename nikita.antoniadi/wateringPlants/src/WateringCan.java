public class WateringCan {
    int capacity;
    int water;

    public WateringCan(int capacity) {
        this.capacity = capacity;
        water = capacity;
    }

    public boolean waterPlant(int waterNeeded) {
        if (waterNeeded <= water) {
            water -= waterNeeded;
            return true;
        }
        return false;
    }

    public void fill() {
        water = capacity;
    }
}
