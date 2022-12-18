package classes;

import java.util.*;
import java.util.stream.IntStream;

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

    public Set<String> getHunterNames(List<MammothPrey> list) {
        Set<String> names = new HashSet<>();
        list.forEach(mp -> names.add(mp.getNickname()));
        return names;
    }

    public int getSumMeat(List<MammothPrey> list, int date) {
        return list.stream().filter(mp -> mp.getYear() >= date && mp.getYear() < date + 3).mapToInt(MammothPrey::getWeight).sum();
    }

    public int getMeatHunter(List<MammothPrey> list, String name) {
        return list.stream().filter(mp -> mp.getNickname().equals(name)).mapToInt(MammothPrey::getWeight).sum();
    }

    public void task(List<MammothPrey> list, int date, boolean isNeedCheckTime) {
        IntStream.range(0, NUMBER_OF_RECORDS).mapToObj(mp ->
                new MammothPrey(randomizeFullNickname(), randomizeWeight(), randomizeYear())).forEach(list::add);
        long time = System.nanoTime();
        Set<String> names = getHunterNames(list);
        int sumMeat = getSumMeat(list, date);
        if (isNeedCheckTime) {
            names.forEach(name -> getMeatHunter(list, name));
            time = System.nanoTime() - time;
            System.out.printf("Elapsed %,9.3f ms\n", time / 1000000.0);
        } else {
            System.out.println("Mammoth statistics:\n");
            list.forEach(System.out::println);

            System.out.println("Names of all hunters who got meat:\n");
            names.forEach(System.out::println);

            System.out.println("The total amount of meat for 3 years from " + date + ": " + sumMeat + '\n');

            System.out.println("The amount of meat that each hunter got:\n");
            names.forEach(name -> System.out.println(name + ": " + getMeatHunter(list, name)));
        }
    }
}