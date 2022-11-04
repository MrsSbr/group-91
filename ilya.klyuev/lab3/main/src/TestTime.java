import controllers.PerformancesEmployeesController;
import storages.EmployeeStorage;

import java.util.logging.Logger;

public class TestTime {
    private final Logger logger;

    public TestTime(Logger logger) {

        this.logger = logger;
    }

    private void testOne() {
        PerformancesEmployeesController performancesEmployeesController = new PerformancesEmployeesController(
                null,
                new EmployeeStorage(logger)
        );

        performancesEmployeesController.addEmployeesTicketsByRandom();
        performancesEmployeesController.getMaxPopularPerformances();
        performancesEmployeesController.getPerformancesNotTickets();
    }

    public long test(int randomGenerationsCount) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < randomGenerationsCount; i++) {
            testOne();
        }

        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
