package deals;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DealsStorage {

    private static final Logger logger = Logger.getLogger(DealsStorage.class.getName());
    private final List<Deal> deals = new ArrayList<>();

    public void addFromFile(String filePath) {
        logger.log(Level.INFO, "Start read file");

        try (var bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Deal deal = Deal.parse(line);
                if (deal == null) {
                    logger.log(Level.SEVERE, "Parse deal error. Stop read");
                    break;
                }
                deals.add(deal);
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "File not found", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Read file exception", e);
        }

        logger.log(Level.INFO, "End read file");
    }

    private Stream<Deal> getDealsAfterDate(LocalDate date) {
        return deals.stream()
                .filter(deal -> deal.getDate().compareTo(date) >= 0);
    }

    private String getMaxIncomeValueString(Map<String, IntSummaryStatistics> incomesStatistic) {
        long maxIncome = incomesStatistic.values().stream()
                .mapToLong(IntSummaryStatistics::getSum)
                .max()
                .orElse(0);

        return incomesStatistic.entrySet().stream()
                .filter(x -> x.getValue().getSum() == maxIncome)
                .map(x -> x.getKey() + " : " + x.getValue().getSum())
                .collect(Collectors.joining("\n"));
    }

    public String getCustomersIncomeStatisticString() {
        return deals.stream()
                .collect(Collectors.groupingBy(Deal::getCustomerName,
                        Collectors.summarizingInt(Deal::getAmount)))
                .entrySet().stream()
                .map(x -> x.getKey() + " : " + x.getValue().getSum())
                .collect(Collectors.joining("\n"));
    }


    public String getMostProfitableMonthsStringForLastYear() {
        return getMaxIncomeValueString(
                getDealsAfterDate(LocalDate.now().minusYears(1))
                        .collect(Collectors.groupingBy(x -> x.getDate().getMonth().toString(),
                                Collectors.summarizingInt(Deal::getAmount)))
        );
    }

    public String getMostEffectiveManagersStringForLastMonth() {
        return getMaxIncomeValueString(
                getDealsAfterDate(LocalDate.now().minusMonths(1))
                        .collect(Collectors.groupingBy(Deal::getManagerName,
                                Collectors.summarizingInt(Deal::getAmount)))
        );
    }
}
