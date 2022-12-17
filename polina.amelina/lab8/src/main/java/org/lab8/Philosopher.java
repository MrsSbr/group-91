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

            synchronized (forkLeft) {
                synchronized (forkRight) {
                    System.out.println(getName() + " хватает две вилки и принимается за еду");

                    try {
                        sleep(random.nextInt(1000, 5000));
                    } catch (InterruptedException ignored) {}

                    System.out.println(getName() + " откладывает вилки и удаляется в глубокие размышления");

                    try {
                        sleep(random.nextInt(500, 2500));
                    } catch (InterruptedException ignored) {}
                }
            }

            try {
                sleep(random.nextInt(500, 2500));
            } catch (InterruptedException ignored) {}

            duration = System.currentTimeMillis() - startTime;
        }
    }
}