package input.console;

import input.EmployeesAddFromInputStream;
import models.Employee;
import factories.ListFactory;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeesAddFromConsole implements EmployeesAddFromInputStream {
    private final Logger logger;

    public EmployeesAddFromConsole(Logger logger) {
        this.logger = logger;
    }

    @Override
    public List<Employee> inputEmployees(int maxEmployeesNumber) {
        List<Employee> employeeList = ListFactory.createList();

        if (maxEmployeesNumber <= 0) {
            logger.log(Level.SEVERE, "inputEmployees: невозможно добавить работников");
            return employeeList;
        }

        ConsoleInput consoleInput = new ConsoleInput(logger);

        int employeesCount = consoleInput.inputIntInRange(
                "Введите количество добавляемых работников",
                1,
                maxEmployeesNumber
        );

        for (int i = 0; i < employeesCount; i++) {
            employeeList.add(Employee.fromConsole(consoleInput));
        }

        return employeeList;
    }
}
