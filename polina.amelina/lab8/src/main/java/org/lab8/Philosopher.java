package org.lab8;

public class Philosopher extends Thread {
    private final Fork forkLeft;
    private final Fork forkRight;

    public Philosopher(
            Fork forkLeft,
            Fork forkRight,
            String name) {

        this.forkLeft = forkLeft;
        this.forkRight = forkRight;

        setName(name);
    }

    @Override
    public void run() {
        while (true) {

            synchronized (forkLeft) {
                synchronized (forkRight) {
                    System.out.println(getName() + " хватает две вилки и принимается за еду");

                    try {
                        sleep(2000);
                    } catch (InterruptedException ignored) {}

                    System.out.println(getName() + " откладывает вилки и удаляется в глубокие размышления");
                }
            }

            try {
                sleep(2000);
            } catch (InterruptedException ignored) {}
        }
    }
}