package deals;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    private Collection<Deal> getDealsAfterDate(LocalDate date) {
        Collection<Deal> dealsAfterDate = new ArrayList<>();
        for (var deal : deals) {
            if (deal.getDate().compareTo(date) >= 0) {
                dealsAfterDate.add(deal);
            }
        }
        return dealsAfterDate;
    }

    private void putMapEntryOnStringBuilder(StringBuilder stringBuilder, Map.Entry<String, Integer> entry) {
        stringBuilder
                .append(entry.getKey())
                .append(" : ")
                .append(entry.getValue())
                .append("\n");
    }

    private String getMaxIncomeValueString(Map<String, Integer> incomesStatistic) {
        int maxIncome = -1;

        for (var incomeEntry : incomesStatistic.entrySet()) {
            if (incomeEntry.getValue() > maxIncome) {
                maxIncome = incomeEntry.getValue();
            }
        }

        StringBuilder maxIncomesEntriesStringBuilder = new StringBuilder();

        for (var incomeEntry : incomesStatistic.entrySet()) {
            if (incomeEntry.getValue() == maxIncome) {
                putMapEntryOnStringBuilder(maxIncomesEntriesStringBuilder, incomeEntry);
            }
        }

        return maxIncomesEntriesStringBuilder.toString();
    }

    public String getCustomersIncomeStatisticString() {
        if (deals.isEmpty()) {
            return "Пока ещё не было совершено ни одной сделки";
        }

        Map<String, Integer> customersIncomeStatistic = new HashMap<>();

        for (var deal : deals) {
            Integer customerIncome = customersIncomeStatistic.get(deal.getCustomerName());
            customersIncomeStatistic.put(deal.getCustomerName(), deal.getAmount() + (customerIncome != null ? customerIncome : 0));
        }

        StringBuilder customersIncomeStatisticsStringBuilder = new StringBuilder();

        for (var customerIncome : customersIncomeStatistic.entrySet()) {
            putMapEntryOnStringBuilder(customersIncomeStatisticsStringBuilder, customerIncome);
        }
        return customersIncomeStatisticsStringBuilder.toString();
    }


    public String getMostProfitableMonthsStringForLastYear() {
        Map<String, Integer> monthsIncomes = new HashMap<>();
        var lastYearDeals = getDealsAfterDate(LocalDate.now().minusYears(1));

        if (lastYearDeals.isEmpty()) {
            return "За прошлый год не было совершено ни одной сделки";
        }

        for (var deal : lastYearDeals) {
            String month = deal.getDate().getMonth().toString();
            Integer monthIncome = monthsIncomes.get(month);
            monthsIncomes.put(month, deal.getAmount() + (monthIncome != null ? monthIncome : 0));
        }

        return getMaxIncomeValueString(monthsIncomes);
    }

    public String getMostEffectiveManagersStringForLastMonth() {
        HashMap<String, Integer> managersIncomes = new HashMap<>();
        var lastMonthDeals = getDealsAfterDate(LocalDate.now().minusMonths(1));

        if (lastMonthDeals.isEmpty()) {
            return "За прошлый месяц не было совершено ни одной сделки";
        }

        for (var deal : lastMonthDeals) {
            Integer managerIncome = managersIncomes.get(deal.getManagerName());
            managersIncomes.put(deal.getManagerName(), deal.getAmount() + (managerIncome != null ? managerIncome : 0));
        }

        return getMaxIncomeValueString(managersIncomes);
    }
}
