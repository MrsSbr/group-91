package models;

import input.console.ConsoleInput;

public class Employee {
    private final Performance firstPerformance;
    private final Performance secondPerformance;

    public Employee(Performance firstPerformance, Performance secondPerformance) {
        this.firstPerformance = firstPerformance;
        this.secondPerformance = secondPerformance;
    }

    public Performance getFirstPerformance() {
        return firstPerformance;
    }

    public Performance getSecondPerformance() {
        return secondPerformance;
    }

    public static Employee fromConsole(ConsoleInput consoleInput) {
        System.out.println("Введите информацию о выбраных спектаклях");

        int firstPerformanceIndex = consoleInput.inputIntInRange(
                "Введите номер 1 спекталя",
                1,
                Performance.getPerformancesCount()
        );
        int secondPerformanceIndex = consoleInput.inputIntInRange(
                "Введите номер 2 спекталя",
                1,
                Performance.getPerformancesCount()
        );

        return new Employee(Performance.fromIndex(firstPerformanceIndex - 1), Performance.fromIndex(secondPerformanceIndex - 1));
    }
}
