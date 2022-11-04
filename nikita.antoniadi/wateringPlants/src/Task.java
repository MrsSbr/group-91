public class Task {
    public static int solution() {
        int capacity = Input.getCapacity();
        int amount = Input.getPlantsAmount();
        int[] plants = Input.getWaterNeeded(amount, capacity);
        int steps = 0;
        WateringCan wateringCan = new WateringCan(capacity);
        for (int i = 0; i < plants.length; i++) {
            if (wateringCan.waterPlant(plants[i])) {
                steps++;
            }
            else {
                steps += 2 * i + 1;
                wateringCan.fill();
                wateringCan.waterPlant(plants[i]);
            }
        }
        return steps;
    }
}
