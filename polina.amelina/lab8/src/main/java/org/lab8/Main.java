package org.lab8;

public class Main {
    public static void main(String[] args) {
        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();
        Fork fork5 = new Fork();

        Philosopher philosopher1 = new Philosopher(fork1, fork2, "Аристотель");
        Philosopher philosopher2 = new Philosopher(fork2, fork3, "Диоген");
        Philosopher philosopher3 = new Philosopher(fork3, fork4, "Пифагор");
        Philosopher philosopher4 = new Philosopher(fork4, fork5, "Платон");
        Philosopher philosopher5 = new Philosopher(fork5, fork1, "Сократ");

        philosopher1.start();
        philosopher2.start();
        philosopher3.start();
        philosopher4.start();
        philosopher5.start();

        try {
            philosopher1.join();
            philosopher2.join();
            philosopher3.join();
            philosopher4.join();
            philosopher5.join();
        } catch (InterruptedException ignored) {}
    }
}