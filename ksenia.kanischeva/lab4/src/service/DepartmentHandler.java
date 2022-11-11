package service;

import models.DepartmentEmployees;

import java.util.*;

public class DepartmentHandler {
    private final Map<Integer, DepartmentEmployees> departments;

    public DepartmentHandler(Map<Integer, DepartmentEmployees> departments){
        this.departments = departments;
    }


    public String getStatement() {
        StringBuilder departmentsInfo = new StringBuilder();
        for (Integer part : departments.keySet()) {
            departmentsInfo.append("\nОтдел ").append(part).append("\n").append(departments.get(part).getInfo());
        }
        return departmentsInfo.toString();
    }

    private static Set<Integer> getDepartmentsWithHighSth(Map<Integer, Double> departmentsInfo) {
        double maxSum = Collections.max(departmentsInfo.values());

        Set<Integer> departmentsWithHighSth = new HashSet<>();

        for (Integer part : departmentsInfo.keySet()) {
            if (departmentsInfo.get(part) == maxSum) {
                departmentsWithHighSth.add(part);
            }
        }

        return departmentsWithHighSth;

    }

    public  Set<Integer> getDepartmentsWithHighSum() {
        Map<Integer, Double> sums = new HashMap<>();
        for (Integer part : departments.keySet()) {
            sums.put(part, departments.get(part).getSumSalary());
        }

        return getDepartmentsWithHighSth(sums);
    }

    public  Set<Integer> getDepartmentsWithHighAvg() {
        Map<Integer, Double> avgs = new HashMap<>();
        for (Integer part : departments.keySet()) {
            avgs.put(part, departments.get(part).getAvgSalary());
        }

        return getDepartmentsWithHighSth(avgs);
    }
}
