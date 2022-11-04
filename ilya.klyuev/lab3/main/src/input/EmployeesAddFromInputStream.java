package input;

import models.Employee;
import java.util.List;

public interface EmployeesAddFromInputStream{
    List<Employee> inputEmployees(int maxEmployeesNumber);
}
