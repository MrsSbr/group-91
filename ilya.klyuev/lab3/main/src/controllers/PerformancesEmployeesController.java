package controllers;

import input.EmployeesAddFromInputStream;
import random.RandomEmployeeGenerator;
import models.Employee;
import models.Performance;
import storages.EmployeeStorage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PerformancesEmployeesController {

    private final EmployeesAddFromInputStream employeesInputFromAddStream;
    private final EmployeeStorage employeeStorage;

    // будем кешировать ответы, чтобы не считать при повторном запросе
    private Set<Performance> maxPopularPerformances = null;
    private Set<Performance> performancesNotTickets = null;

    public PerformancesEmployeesController(EmployeesAddFromInputStream employeesInputFromInputStream, EmployeeStorage employeeStorage) {

        this.employeesInputFromAddStream = employeesInputFromInputStream;
        this.employeeStorage = employeeStorage;
    }

    public Set<Performance> getMaxPopularPerformances() {
        if (maxPopularPerformances != null) {
            return maxPopularPerformances;
        }

        int maxTicketsCount = 0;

        // calculate max
        for (var performance : Performance.values()) {
            maxTicketsCount = Math.max(maxTicketsCount, performance.getTicketsCount());
        }

        maxPopularPerformances = new HashSet<>();

        // select max
        for (var performance : Performance.values()) {
            if (performance.getTicketsCount() == maxTicketsCount) {
                maxPopularPerformances.add(performance);
            }
        }

        return maxPopularPerformances;
    }

    public Set<Performance> getPerformancesNotTickets() {
        if (performancesNotTickets != null) {
            return performancesNotTickets;
        }

        performancesNotTickets = new HashSet<>();

        for (Performance performance : Performance.values()) {
            if (performance.getTicketsCount() == 0) {
                performancesNotTickets.add(performance);
            }
        }

        return performancesNotTickets;
    }

    public void addEmployeesTicketsFromInputStream() {
        var newEmployees = employeesInputFromAddStream.inputEmployees(employeeStorage.getAllowedEmployeesCount());
        employeeStorage.addEmployees(newEmployees);
        updatePerformances(newEmployees);
    }

    public void addEmployeesTicketsByRandom() {
        var newEmployees = RandomEmployeeGenerator.generateEmployees(employeeStorage.getAllowedEmployeesCount());
        employeeStorage.addEmployees(newEmployees);
        updatePerformances(newEmployees);
    }

    private void updatePerformances(List<Employee> newEmployees) {
        for (Employee employee : newEmployees) {
            employee.getFirstPerformance().addTicket();
            employee.getSecondPerformance().addTicket();
        }
        resetPerformancesCache();
    }

    private void resetPerformancesCache() {
        maxPopularPerformances = null;
        performancesNotTickets = null;
    }
}
