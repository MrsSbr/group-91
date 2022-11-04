package storages;

import factories.ListFactory;
import models.Employee;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeStorage {
    private final List<Employee> employees;
    private final Logger logger;

    private final int MAX_EMPLOYEES_COUNT = 12000;

    public EmployeeStorage(Logger logger) {
        this.logger = logger;
        employees = ListFactory.createList();
    }

    public void addEmployees(Collection<Employee> employees) {
        if (employees.size() + this.employees.size() <= MAX_EMPLOYEES_COUNT) {
            this.employees.addAll(employees);
        } else {
            logger.log(Level.SEVERE, "Превышено максимальное количество работников");
        }
    }

    public int getEmployeesCount() {
        return employees.size();
    }

    public int getAllowedEmployeesCount() {
        return MAX_EMPLOYEES_COUNT - getEmployeesCount();
    }
}
