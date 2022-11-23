package service;

import models.DepartmentEmployees;

import java.util.*;
import java.util.stream.Collectors;

public class DepartmentHandler {
    private final Map<Integer, DepartmentEmployees> departments;

    public DepartmentHandler(Map<Integer, DepartmentEmployees> departments){
        this.departments = departments;
    }


    public String getStatement() {

        return departments.entrySet().stream()
                .map(pair ->"\nОтдел " + pair.getKey() + "\n" + pair.getValue().getInfo())
                .reduce("", (partialInfoResult, info)-> partialInfoResult + info);

    }

    private static Set<Integer> getDepartmentsWithHighSth(Map<Integer, Double> departmentsInfo) {

        double max = departmentsInfo.values().stream()
                .max(Double::compareTo)
                .orElse(0.0);

        return departmentsInfo.entrySet().stream()
                .filter(pair -> pair.getValue() == max)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    public  Set<Integer> getDepartmentsWithHighSum() {
        Map<Integer, Double> sums = departments.entrySet().stream()
                                   .collect(Collectors.toMap(Map.Entry::getKey, pair-> pair.getValue().getSumSalary()));
        return getDepartmentsWithHighSth(sums);
    }

    public  Set<Integer> getDepartmentsWithHighAvg() {
        Map<Integer, Double> avgs = departments.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, pair-> pair.getValue().getAvgSalary()));
        return getDepartmentsWithHighSth(avgs);
    }
}
