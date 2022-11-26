package models;

import java.util.ArrayList;
import java.util.List;

public class DepartmentEmployees {
    private final List<Employee> employeeList = new ArrayList<>();

    public void addEmployee(Employee e) {
        employeeList.add(e);
    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof DepartmentEmployees) {
            return employeeList.equals(((DepartmentEmployees) o).employeeList);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return employeeList.hashCode();
    }

    public String getInfo() {
        return employeeList.stream()
                .map(Employee::toString)
                .reduce("", (partialInfoResult, empl) -> partialInfoResult + empl );
    }

    public double getAvgSalary() {
        return employeeList.stream().mapToInt(Employee::getSalary).average().orElse(0);
    }

    public double getSumSalary() {
        return employeeList.stream().mapToInt(Employee::getSalary).sum();
    }


}
