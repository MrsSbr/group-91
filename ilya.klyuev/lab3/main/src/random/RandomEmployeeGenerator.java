package random;

import models.Employee;
import factories.ListFactory;
import models.Performance;

import java.util.List;

public class RandomEmployeeGenerator {
    private RandomEmployeeGenerator() {

    }

    private static Performance generateRandomPerformance() {
        return Performance.fromIndex((int) (Math.random() * Performance.values().length));
    }

    public static Employee generateEmployee() {
        Performance firstPerformance = generateRandomPerformance();
        Performance secondPerformance = generateRandomPerformance();
        return new Employee(firstPerformance, secondPerformance);
    }

    public static List<Employee> generateEmployees(int employeesNumber) {
        List<Employee> employeeList = ListFactory.createList();

        for (int i = 0; i < employeesNumber; i++) {
            employeeList.add(generateEmployee());
        }

        return employeeList;
    }
}
