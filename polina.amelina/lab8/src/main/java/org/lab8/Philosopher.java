package org.lab8;

import java.util.Random;

public class Philosopher extends Thread {

    private final Fork forkLeft;
    private final Fork forkRight;
    private final long programDurationInMillis;

    public Philosopher(
            Fork forkLeft,
            Fork forkRight,
            String name,
            long programDurationInMillis) {

        this.forkLeft = forkLeft;
        this.forkRight = forkRight;
        this.programDurationInMillis = programDurationInMillis;

        setName(name);
    }

    @Override
    public void run() {

        Random random = new Random();
        long startTime = System.currentTimeMillis();
        long duration = 0;

        while (duration < programDurationInMillis) {
            try {

                if (forkLeft.number() < forkRight.number()) {
                    synchronized (forkLeft) {
                        synchronized (forkRight) {
                            useForks(forkLeft, forkRight, random);
                        }
                    }

                } else {
                    synchronized (forkRight) {
                        synchronized (forkLeft) {
                            useForks(forkRight, forkLeft, random);
                        }
                    }
                }

                sleep(random.nextInt(500, 2500));

            } catch (InterruptedException ignored) {}

            duration = System.currentTimeMillis() - startTime;
        }
    }

    private void useForks(Fork forkFirst, Fork forkSecond, Random random) throws InterruptedException {

        System.out.println(getName() + " хватает вилки " + forkFirst.number() + " и " + forkSecond.number() + " и принимается за еду");
        sleep(random.nextInt(1000, 5000));

        System.out.println(getName() + " откладывает вилки " + forkFirst.number() + " и " + forkSecond.number() + " и удаляется в глубокие размышления");
        sleep(random.nextInt(500, 2500));
    }
}