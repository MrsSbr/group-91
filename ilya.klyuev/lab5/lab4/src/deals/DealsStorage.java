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

    private Set<String> getKeysByMaxValueFromStatistic(Map<String, Integer> statistic) {
        int maxValue = statistic.values().stream()
                .max(Integer::compare)
                .orElse(0);

        return statistic.entrySet().stream()
                .filter(x -> x.getValue() == maxValue)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    private Stream<Deal> getDealsAfterDate(LocalDate date) {
        return deals.stream()
                .filter(deal -> deal.getDate().isAfter(date));
    }

    public Map<String, Integer> getCustomersIncomeStatistic() {
        return deals.stream()
                .collect(Collectors.groupingBy(Deal::getCustomerName,
                        Collectors.summingInt(Deal::getAmount)));
    }

    public Set<String> getMostProfitableMonthsForLastYear() {
        var monthsStatisticForLastYear =
                getDealsAfterDate(LocalDate.now().minusYears(1))
                        .collect(Collectors.groupingBy(x -> x.getDate().getMonth().toString(),
                                Collectors.summingInt(Deal::getAmount)));

        return getKeysByMaxValueFromStatistic(monthsStatisticForLastYear);
    }

    public Set<String> getMostEffectiveManagersForLastMonth() {
        var managersStatisticForLastMonth =
                getDealsAfterDate(LocalDate.now().minusMonths(1))
                        .collect(Collectors.groupingBy(Deal::getManagerName,
                                Collectors.summingInt(Deal::getAmount)));

        return getKeysByMaxValueFromStatistic(managersStatisticForLastMonth);
    }
}
