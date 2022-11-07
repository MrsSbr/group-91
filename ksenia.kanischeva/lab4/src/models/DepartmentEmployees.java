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
        StringBuilder departmentInfo = new StringBuilder();
        for (Employee e : employeeList) {
            departmentInfo.append(e.toString()).append("\n");
        }
        return departmentInfo.toString();
    }

    public double getAvgSalary() {
        return getSumSalary() / employeeList.size();
    }

    public double getSumSalary() {
        int sum = 0;
        for (Employee e : employeeList) {
            sum += e.getSalary();
        }
        return sum;
    }


}
