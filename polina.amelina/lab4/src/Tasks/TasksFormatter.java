package Tasks;

import MethodNameGetter.MethodNameGetter;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class TasksFormatter {
    private static final Logger logger = Logger.getGlobal();

    public TasksFormatter() {}

    public String getAvgPrepTimeByDrinkFormattedResult(Map<String, LocalTime> avgPrepTime) {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        String result;

        if (avgPrepTime == null) {
            result = "Нет ни одного приготовленного напитка";

        } else {
            StringBuilder resultBuilder = new StringBuilder("Среднее время приготовления каждого напитка: ");

            for (String name : avgPrepTime.keySet()) {
                resultBuilder.append(System.lineSeparator()).append(name).append(": ").append(avgPrepTime.get(name));
            }

            result = resultBuilder.toString();
        }

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), result);
        return result;
    }

    public String getBusiestWeekdayHoursFormattedResult(List<Integer> busiestHours) {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        String result;

        if (busiestHours == null) {
            result = "По будням нет посетителей";

        } else {
            StringBuilder resultBuilder = new StringBuilder("Самые загруженные часы по будням: ").append(busiestHours.get(0).toString());

            for (int i = 1; i < busiestHours.size(); i++) {
                resultBuilder.append(", ").append(busiestHours.get(i).toString());
            }

            result = resultBuilder.toString();
        }

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), result);
        return result;
    }

    public String getMostPopularDrinksFormattedResult(List<String> mostPopularDrinks) {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        String result;

        if (mostPopularDrinks == null) {
            result = "С 7 до 12 утра нет заказов";

        } else {
            StringBuilder resultBuilder = new StringBuilder("Напитки, которые чаще всего заказывают с 7 до 12 утра: ").append(mostPopularDrinks.get(0));

            for (int i = 1; i < mostPopularDrinks.size(); i++) {
                resultBuilder.append(", ").append(mostPopularDrinks.get(i));
            }

            result = resultBuilder.toString();
        }

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), result);
        return result;
    }

    public String getOptimalDrinkFormattedResult(String optimalDrink) {

        logger.entering(getClass().getName(), MethodNameGetter.getMethodName());

        String result = optimalDrink == null
                        ? "Нет ни одного проданного напитка"
                        : "Напиток с наилучшим соотношением цена/время: " + optimalDrink;

        logger.exiting(getClass().getName(), MethodNameGetter.getMethodName(), result);
        return result;
    }
}