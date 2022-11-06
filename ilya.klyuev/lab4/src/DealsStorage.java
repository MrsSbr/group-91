import models.Customer;
import models.Deal;
import models.Manager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.Month;

public class DealsStorage {

    private static final Logger logger = Logger.getLogger(DealsStorage.class.getName());
    // будем хранить в отсортированном виде, чтобы быстрее получать статистику за последний год/месяц
    private final SortedSet<Deal> deals = new TreeSet<>();

    public boolean add(Deal deal) {
        if (deals.contains(deal)) {
            logger.log(Level.SEVERE, "Add repeating deal");
            return false;
        } else {
            deals.add(deal);
            return true;
        }
    }

    public void addFromFile(String filePath) {
        logger.log(Level.INFO, "Start read file");

        try (var bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Deal deal = Deal.parse(line);
                // deal not parsed or not added => stop read
                if (deal == null || !add(deal)) {
                    logger.log(Level.SEVERE, "Add deal error. Stop read");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "File not found", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Read file exception", e);
        }

        logger.log(Level.INFO, "End read file");
    }

    private Set<Deal> getDealsAfterDate(LocalDate date) {
        Set<Deal> dealsAfterDate = new HashSet<>();
        for (var deal : deals) {
            if (deal.getDate().compareTo(date) < 0) {
                break;
            }
            dealsAfterDate.add(deal);
        }
        return dealsAfterDate;
    }

    private <KeyType> String getMaxIncomeString(Set<Map.Entry<KeyType, Integer>> entrySet) {
        int maxIncome = -1;

        for (var incomeEntry : entrySet) {
            if (incomeEntry.getValue() > maxIncome) {
                maxIncome = incomeEntry.getValue();
            }
        }

        StringBuilder maxIncomesEntriesStringBuilder = new StringBuilder();

        for (var incomeEntry : entrySet) {
            if (incomeEntry.getValue() == maxIncome) {
                maxIncomesEntriesStringBuilder
                        .append(incomeEntry.getKey())
                        .append(" : ")
                        .append(maxIncome)
                        .append("\n");
            }
        }

        return maxIncomesEntriesStringBuilder.toString();
    }

    public String getAllDealsString() {
        StringBuilder dealsStringBuilder = new StringBuilder();
        for (var deal : deals) {
            dealsStringBuilder
                    .append(deal)
                    .append("\n");
        }
        return dealsStringBuilder.toString();
    }

    public String getCustomersIncomeStatisticString() {
        if (deals.isEmpty()) {
            return "Пока ещё не было совершено ни одной сделки";
        }

        Map<Customer, Integer> customersIncomeStatistic = new HashMap<>();

        for (var deal : deals) {
            Integer customerIncome = customersIncomeStatistic.get(deal.getCustomer());
            customersIncomeStatistic.put(deal.getCustomer(), deal.getAmount() + (customerIncome != null ? customerIncome : 0));
        }

        StringBuilder customersIncomeStatisticsStringBuilder = new StringBuilder();

        for (var customerIncome : customersIncomeStatistic.entrySet()) {
            customersIncomeStatisticsStringBuilder
                    .append(customerIncome.getKey())
                    .append(" : ")
                    .append(customerIncome.getValue())
                    .append("\n");
        }
        return customersIncomeStatisticsStringBuilder.toString();
    }


    public String getMostProfitableMonthsStringForLastYear() {
        Map<Month, Integer> monthsIncomes = new HashMap<>();
        var lastYearDeals = getDealsAfterDate(LocalDate.now().minusYears(1));

        if (lastYearDeals.isEmpty()) {
            return "За прошлый год не было совершено ни одной сделки";
        }

        for (var deal : lastYearDeals) {
            Integer monthIncome = monthsIncomes.get(deal.getDate().getMonth());
            monthsIncomes.put(deal.getDate().getMonth(), deal.getAmount() + (monthIncome != null ? monthIncome : 0));
        }

        return getMaxIncomeString(monthsIncomes.entrySet());
    }

    public String getMostEffectiveManagersStringForLastMonth() {
        HashMap<Manager, Integer> managersIncomes = new HashMap<>();
        var lastMonthDeals = getDealsAfterDate(LocalDate.now().minusMonths(1));

        if (lastMonthDeals.isEmpty()) {
            return "За прошлый месяц не было совершено ни одной сделки";
        }

        for (var deal : lastMonthDeals) {
            Integer managerIncome = managersIncomes.get(deal.getManager());
            managersIncomes.put(deal.getManager(), deal.getAmount() + (managerIncome != null ? managerIncome : 0));
        }

        return getMaxIncomeString(managersIncomes.entrySet());
    }
}
