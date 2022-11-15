package Classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Task {
    private static final int NUMBER_OF_RECORDS = 8573;
    private static final List<String> headNickname = new ArrayList<>(Arrays.asList("Big", "Strong", "Small", "Tall",
            "Smart", "Sharp", "Dumb", "Angry", "Clumsy", "Slow", "Curious", "Grumpy", "Kind", "Rude", "Shy"));
    private static final List<String> tailNickname = new ArrayList<>(Arrays.asList("Head", "Axe", "Heart", "Stone",
            "Fish", "Bug", "Brick", "Snow", "Rain", "Fire", "Hunter", "Cultivator", "Club", "Cave"));

    public String randomizeFullNickname() {
        Random rnd = new Random();
        int headIndex = rnd.nextInt(0, headNickname.size());
        int tailIndex = rnd.nextInt(0, tailNickname.size());
        return headNickname.get(headIndex) + " " + tailNickname.get(tailIndex);
    }

    public int randomizeYear() {
        Random rnd = new Random();
        return rnd.nextInt(-15000, -10000);
    }

    public int randomizeWeight() {
        Random rnd = new Random();
        return rnd.nextInt(10000, 15000);
    }

    public List<String> getHunterNames(List<MammothPrey> list) {
        List<String> names = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_RECORDS; i++) {
            if (!names.contains(list.get(i).getNickname())) {
                names.add(list.get(i).getNickname());
            }
        }
        return names;
    }

    public int getSumMeat(List<MammothPrey> list, int date) {
        int sum = 0;
        for (int i = 0; i < NUMBER_OF_RECORDS; i++) {
            if (list.get(i).getYear() >= date && list.get(i).getYear() < date + 3) {
                sum += list.get(i).getWeight();
            }
        }
        return sum;
    }

    public int getMeatHunter(List<MammothPrey> list, String name) {
        int sum = 0;
        for (int i = 0; i < NUMBER_OF_RECORDS; i++) {
            if (list.get(i).getNickname().equals(name)) {
                sum += list.get(i).getWeight();
            }
        }
        return sum;
    }

    public void task(List<MammothPrey> list, int date, boolean isNeedCheckTime) {
        for (int i = 0; i < NUMBER_OF_RECORDS; i++) {
            list.add(new MammothPrey(randomizeFullNickname(), randomizeWeight(), randomizeYear()));
        }
        long time = System.nanoTime();
        List<String> names = getHunterNames(list);
        int sumMeat = getSumMeat(list, date);
        if (isNeedCheckTime) {
            for (String name : names) {
                getMeatHunter(list, name);
            }
            time = System.nanoTime() - time;
            System.out.printf("Elapsed %,9.3f ms\n", time / 1000000.0);
        } else {
            System.out.println("Mammoth statistics:\n");
            for (int i = 0; i < NUMBER_OF_RECORDS; i++) {
                System.out.println(list.get(i).toString());
            }

            System.out.println("Names of all hunters who got meat:\n");
            for (String name : names) {
                System.out.println(name);
            }

            System.out.println("The total amount of meat for 3 years from " + date + ": " + sumMeat + '\n');

            System.out.println("The amount of meat that each hunter got:\n");
            for (String name : names) {
                System.out.println(name + ": " + getMeatHunter(list, name));
            }
        }
    }
}
