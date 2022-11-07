package service;

import models.DepartmentEmployees;

import java.util.*;

public class DepartmentHandler {
//    private final Map<Integer, DepartmentEmployees> departments;
//
//    public DepartmentHandler(Map<Integer, DepartmentEmployees> departments){
//        this.departments = departments;
//    }


    public static String getStatement(Map<Integer, DepartmentEmployees> departments){
        StringBuilder departmentsInfo = new StringBuilder();
        for (Integer part : departments.keySet()){
            departmentsInfo.append("\nОтдел ").append(part).append("\n").append(departments.get(part).getInfo());
        }
        return departmentsInfo.toString();
    }

    private static Set<Integer> getDepartmentsWithHighSth(Map<Integer, Double> departmentsInfo){
        double maxSum = Collections.max(departmentsInfo.values());
//        for (Integer part : departmentsInfo.keySet()) {
//            double sum = departmentsInfo.get(part);
//            if (sum > maxSum){
//                maxSum = sum;
//            }
//        }

        Set<Integer> departmentsWithHighSth = new HashSet<>();

        for (Integer part : departmentsInfo.keySet()){
            if (departmentsInfo.get(part) == maxSum){
                departmentsWithHighSth.add(part);
            }
        }

        return departmentsWithHighSth;

    }

    public static Set<Integer> getDepartmentsWithHighSum(Map<Integer, DepartmentEmployees> departments){
        Map<Integer, Double> sums = new HashMap<>();
        for (Integer part : departments.keySet()) {
            sums.put(part, departments.get(part).getSumSalary());
        }

        return  getDepartmentsWithHighSth(sums);
    }

    public static Set<Integer> getDepartmentsWithHighAvg(Map<Integer, DepartmentEmployees> departments){
        Map<Integer, Double> avgs = new HashMap<>();
        for (Integer part : departments.keySet()) {
            avgs.put(part, departments.get(part).getAvgSalary());
        }

        return  getDepartmentsWithHighSth(avgs);
    }
}
