package org.lab8;

public class Main {

    private static final String PHILOSOPHER_NAME_1 = "Аристотель";
    private static final String PHILOSOPHER_NAME_2 = "Диоген";
    private static final String PHILOSOPHER_NAME_3 = "Пифагор";
    private static final String PHILOSOPHER_NAME_4 = "Платон";
    private static final String PHILOSOPHER_NAME_5 = "Сократ";
    private static final long PROGRAM_DURATION_IN_MILLIS = 30000;

    public static void main(String[] args) {

        Fork fork1 = new Fork();
        Fork fork2 = new Fork();
        Fork fork3 = new Fork();
        Fork fork4 = new Fork();
        Fork fork5 = new Fork();

        Thread philosopher1 = new Philosopher(fork1, fork2, PHILOSOPHER_NAME_1, PROGRAM_DURATION_IN_MILLIS);
        Thread philosopher2 = new Philosopher(fork2, fork3, PHILOSOPHER_NAME_2, PROGRAM_DURATION_IN_MILLIS);
        Thread philosopher3 = new Philosopher(fork3, fork4, PHILOSOPHER_NAME_3, PROGRAM_DURATION_IN_MILLIS);
        Thread philosopher4 = new Philosopher(fork4, fork5, PHILOSOPHER_NAME_4, PROGRAM_DURATION_IN_MILLIS);
        Thread philosopher5 = new Philosopher(fork5, fork1, PHILOSOPHER_NAME_5, PROGRAM_DURATION_IN_MILLIS);

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